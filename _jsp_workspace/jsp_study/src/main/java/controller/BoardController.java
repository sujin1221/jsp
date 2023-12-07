package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.PagingVO;
import domain.boardVO;
import handler.FileRemoveHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//log 기록 객체 생성
	private static final Logger log = 
			LoggerFactory.getLogger(BoardController.class);
	//jsp에서 받은 요청을 처리 후 그 결과를 다른 jsp로 보내는 역할을 함
	private RequestDispatcher rdp;
	private String desPage; //목적지 주소를 지정하는 변수
	private int isOk; //db 구문 체크 값 저장변수
	private String savePath; //파일 저장 경로를 저장
	
	//controller <-> service
	private BoardService bsv; //인터페이스로 생성
       
    public BoardController() {
        //생성자   	
    	bsv = new BoardServiceImpl(); //class로 생성
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//실제 처리 메서드
		log.info("필요한 로그 띄우기");
		//매개변수의 객체의 인코딩 설정
		request.setCharacterEncoding("utf-8"); //요청
		response.setCharacterEncoding("utf-8"); //응답
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI(); //jsp에서 오는 요청 주소를 받는 설정 => uri에 저장
		System.out.println("sysout을 통한 로그 >> "+uri);
		log.info("log객체를 통한 로그 >>"+uri);
		String path = uri.substring(uri.lastIndexOf("/")+1); //register
		log.info("실 요청 경로 >> "+path);
		String destPage = null;
		switch(path) {
		case "register":
			destPage = "/board/register.jsp";
			break;
		case "insert":			
			//jsp에서 데이터를 입력 후 controller로 전송
			//DB에 등록한 후 => index.jsp로 이동
			try {
				//파일을 업로드 할 물리적인 경로 설정
				savePath = getServletContext().getRealPath("/_fileUpload"); //실제 저장할 경로
				File fileDir = new File(savePath); //파일 경로를 가진 파일 객체가 생성됨
				log.info("저장위치 >>> {} "+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); //저장할 위치를 파일 객체로 지정 => 파일 객체로만 들어갈 수 있음
				fileItemFactory.setSizeThreshold(1024*1024*3); //파일 저장을 위한 임시 메모리 설정 => byte 단위
				
				boardVO bvo = new boardVO(); //값은 없지만 미리 객체 설정...
				//multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				//파라미터들을 파싱해서 파일 아이템으로 변환해서 리스트에 넣어주기
				List<FileItem> itemList = fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString("utf-8")); //string 형태로 받을건데 인코딩해서 넘겨주세요						
						break;
						
					case "writer":
						bvo.setWriter(item.getString("utf-8")); //한글이 깨지지않도록...
						break;
						
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
						
					case "image_file":
						//이미지가 있는지 먼저 체크 (없을 수 있음)
						//이미지의 존재유무는 null로 확인하지 않음 사이즈로 확인함!!
						if(item.getSize() > 0) { //데이터의 크기를 byte 단위로 리턴, 크기가 0보다 크다면 값이 있는지 알 수 있음!
							String fileName = item.getName()
									//경로를 포함한 이름을 가져옴
									//File.separator => 파일 경로의 기호를 저장
									.substring(item.getName().lastIndexOf(File.separator)+1); //이름만 분리
							//시스템의 시간을 이용하여 파일을 구분   ex)시간_dog.jpg
							fileName = System.currentTimeMillis()+"_"+fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName); //모든 경로
							log.info("uploadFilePath >>> {} "+uploadFilePath.toString());
							//저장
							try {
								item.write(uploadFilePath); //해당 자바 객체를 디스크에 저장
								bvo.setImageFile(fileName); //bvo에 저장할 값 설정
								//썸네일 작업 => 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75, 75)
								.toFile(new File(fileDir+File.separator+"_th_"+fileName)); 
							} catch (Exception e) {
								log.info(">>> file writer on disk error");
								e.printStackTrace();
							}
						}
						break;
					}
				}
				isOk = bsv.register(bvo);
				log.info("board register >>> {} ",isOk>0 ? "success":"fail");
				destPage = "/index.jsp"; //목적지 주소
				
				//fileUpload 없을 경우 insert 구문
				//jsp에서 가져온 제목, 작성자, 내용을 꺼내오기
//				String title = request.getParameter("title");
//				String writer = request.getParameter("writer");
//				String content = request.getParameter("content"); 
//				log.info(">>>> insert check 1");
//				boardVO bvo = new boardVO(title, writer, content);
//				log.info("insert bvo >>> {} "+bvo);
//				//만들어진 bvo를 db에 저장
//				isOk = bsv.register(bvo);
//				log.info("board register >>> {} ",isOk>0 ? "success":"fail");
//				destPage = "/index.jsp";				
			} catch (Exception e) {
				log.info("insert error");
				e.printStackTrace();
			}
			break;			
		case "list":
			try {
				//index에서 list 버튼을 클릭하면 
				//컨트롤러에서 DB로 전체 리스트 요청
				//DB에서 받아온 전체 리스트를 가지고 list.jsp에 뿌리기
				log.info("list check 1");
				PagingVO pgvo = new PagingVO(); // 1/10/ 0
				if(request.getParameter("pageNo")!= null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					String type = request.getParameter("type");
					String keyword = request.getParameter("keyword");
					log.info(">>>>> pageNo / qty >> "+pageNo+" / "+qty+" / "+type+" / "+keyword);
					pgvo = new PagingVO(pageNo, qty, type, keyword); //파라미터가 있다면 이렇게 받아주셩셩
					//pgvo.setType(type); => 이렇게 추가해도 됨
				}
				//검색어를 반영한 리스트				
				List<boardVO> list = bsv.getList(pgvo);				
				log.info("list >>> {} "+ list);
				
				//검색한 값의 게시글 카운트
				int totalCount = bsv.getTotal(pgvo); //db에서 전체 게시글 수 가져오기
				log.info("totalCount >>>>> {} "+totalCount);
				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				//list를 jsp로 전송				
				request.setAttribute("list", list);
				request.setAttribute("ph", ph);
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
				//jsp에서 보낸 bno를 받아서
				//해당 번호의 전체값을 조회하여 detail.jsp에 뿌리기
				//모든 파라미터는 string으로 리턴됨
				int bno = Integer.parseInt(request.getParameter("bno")); //string에서 int로 형변환
				log.info("detail check 1");
				boardVO bvo = bsv.getDetail(bno);
				log.info("detail bvo >>>>> {}", bvo);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				//수정 할 데이터의 bno를 받아서 수정 페이지로 보내서
				//modify.jsp를 띄우는 역할
				int bno = Integer.parseInt(request.getParameter("bno"));
				boardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/modify.jsp";
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}	
			break;
			
		case "edit":
			try {
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath); //새로운 경로 만들기
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); //저장 경로 설정
				fileItemFactory.setSizeThreshold(3*1024*1024); //임시 저장 용량 설정 3M
				
				boardVO bvo = new boardVO();
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				String old_file = null;
				
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString("utf-8")));
						break;						
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file":
						old_file = item.getString("utf-8"); //이전 이미지 파일의 보관용
						break;
					case "new_file":
						//새로운 파일은 등록이 될 수도 있고 안될 수도 있음
						if(item.getSize()>0) {
							//새로운 등록 파일이 있다면...
						    if(old_file != null) {
						    	//old_file의 삭제 작업
						    	//파일 삭제 핸들러를 통해서 파일 삭제 작업을 진행
						    	FileRemoveHandler fileHandler = new FileRemoveHandler();						    	
						    	isOk = fileHandler.deleteFile(old_file, savePath);					    	
						    }
						    //새로운 파일에 대한 객체 작업
						    String fileName = item.getName()
						    		.substring(item.getName().lastIndexOf(File.separator)+1);
						    log.info("new file name >>>>> {} "+fileName);
						    
						    fileName = System.currentTimeMillis()+"_"+fileName;
						    File uploadFilePath = new File(fileDir+File.separator+fileName);
						    try {
								item.write(uploadFilePath);
						    	bvo.setImageFile(fileName);
						    	//썸네일 작업
						    	Thumbnails.of(uploadFilePath).size(75, 75)
						    	.toFile(new File(fileDir+File.separator+"_th_"+fileName));
							} catch (Exception e2) {
								log.info("file writer update error!");
								e2.printStackTrace();
							}
						} else {
							//기존 이미지 파일은 있지만 새로운 이미지 파일이 존재하지 않을 경우...
							bvo.setImageFile(old_file); //기존 객체를 bvo에 담기
						}
						break;
					}
				}
				isOk = bsv.modify(bvo);
				log.info("edit >> {} ",isOk>0 ? "ok":"fail");
				destPage = "list"; //내부 list case로 이동
								
				//파라미터로 받은 bno, title, content 데이터를
				//db에 수정하여 넣고, list로 이동
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
//				boardVO bvo = new boardVO(bno, title, content);
//				log.info("edit check 1");
//				log.info("edit >>> {}"+bvo);
//				isOk = bsv.modify(bvo);
//				log.info("edit >> {} ",isOk>0 ? "ok":"fail");
//				destPage = "list"; //내부 list case로 이동
				
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("remove check 1");
				isOk = bsv.remove(bno);
				log.info("remove >>> {} ",isOk>0 ? "ok":"fail");
				destPage = "list";
			} catch (Exception e) {
				log.info("remove error");
				e.printStackTrace();
			}
			break;
			
		}
		
		//목적지 주소로 데이터를 전달(RequestDispatcher)
		rdp = request.getRequestDispatcher(destPage); //정보를 가져와서 목적지 주소를 실어서 저장해서 rdp에 넣어주기
		rdp.forward(request, response); //요청에 필요한 객체를 가지고 destPage 경로로 전송
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get으로 들어오는 요청을 처리하는 메서드
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post으로 들어오는 요청을 처리하는 메서드
		service(request, response);
	}

}
