package controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileRemoveHanlder;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 로그 기록 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	// jsp에서 받은 요청을 처리, 처리결과를 다른 jsp로 보내는 역할을 하는 객체
	private RequestDispatcher rdp;
	private String destPage;// 목적지 주소를 저장하는 변수
	private String savePath; // 파일저장경로를 저장하는 변수
	private int isOk; // DB 구문 체크 값 저장 변수

	// controller <-> service
	private BoardService bsv; // interface로 생성

	public BoardController() {
		// 생성자
		bsv = new BoardServiceImpl(); // class로 생성 bsv를 구현할 객체
	}

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//실제 처리 메서드
		log.info("필요한 로그 띄우기 가능.");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI(); //jsp에서 오는 요청주소를 받는 설정
		System.out.println("sysout을 통한 로그 >>" + uri);
		log.info("log객체를 통한 로그>> " + uri);
		String path = uri.substring(uri.lastIndexOf("/")+1); //register
		
		log.info("실 요청 경로>>> " + path);
		
		switch(path) {
		case "register" :
			destPage = "/board/register.jsp";
			break;
		case "insert" :
			//jsp에서 가져온 제목,작성자, 콘텐츠를 꺼내오기
			//jsp에서 데이터를 입력 후 => controller로 전송
			//DB에 등록한 후 => index.jsp로 이동
			
			try {
				//파일을 업로드할 물리적인 경로 설정
				
				savePath = getServletContext().getRealPath("/_fileUpload"); // context = 설정에 필요한정보들을 가져옴
				File fileDir = new File(savePath);
				log.info("저장위치>>> {}", savePath);
				
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); //저장할 위치를 file객체로 지정
				fileItemFactory.setSizeThreshold(1024*1024*3); //파일저장을 위한 임시 메모리 용량 설정
				
				//미리 객체 설정
				BoardVO bvo = new BoardVO();
				
				//multipart/for-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload 
				= new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file":
						//이미지 있는 체크
						if(item.getSize() > 0) { //데이터의 크기를 바이트 단위로 리턴 크기가 0보다 큰지 체크
							String fileName = item.getName()
									.substring(item.getName().lastIndexOf("FileSeparator")+1);
							log.info("iamge_file check 1");
							fileName = System.currentTimeMillis() + "_" + fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("uploadFilePath>>> {}" + uploadFilePath.toString());					

							try {
								item.write(uploadFilePath);
								bvo.setImageFile(fileName); 
								Thumbnails.of(uploadFilePath) //썸넬
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							}catch (Exception e) {
								log.info(">>> file writer on disk error");	
								e.printStackTrace();
								}		
						}
						break;
					default:
						break;
					}
				}
				
				isOk = bsv.register(bvo);
				log.info("board register >>>> {} ", isOk > 0 ? "OK" : "Fail");
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("intser error");
				e.printStackTrace();
			}	
			break;

		case "list" :
				try {
					log.info("list check 1");
					PagingVO pgvo = new PagingVO(); 
					if(request.getParameter("pageNo") != null) {
						int pageNo = Integer.parseInt(request.getParameter("pageNo"));
						int qty = Integer.parseInt(request.getParameter("qty"));
						String type = request.getParameter("type");
						String keyword = request.getParameter("keyword");
						log.info(">>>pangeNo/qty "+pageNo+"/" +qty + " / " + type + " / " + keyword);
						pgvo = new PagingVO(pageNo,qty,type,keyword);
						
					}
					
					List<BoardVO>list = bsv.getList(pgvo);
					//검색한 값의 게시글 카운트
					int totalCount = bsv.getTotalcount(pgvo); //db에서 전체 게시글수 가져오기
					log.info("totalCount >>> {}" + totalCount);
					PagingHandler ph = new PagingHandler(pgvo, totalCount);
					//list를 jsp로 전송
					
					
					log.info("list>>>{}"+list);
					log.info("ph>>>{}"+ph);
					
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
				//해당 번호의 전체값을 조회하여 detail.jsp 에 뿌리기
				int bno = Integer.parseInt( request.getParameter("bno"));
				
				log.info("detail check 1");
				
				BoardVO bvo = bsv.getDetail(bno);;
				log.info("detail bvo>>> {} ", bvo);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}

			break;

		case "modify" :
			try {
				//수정할 데이터의 bno를 바당서 수정페이지로 보내서
				//modify.jsp를 띄우는 역할
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.getDetail(bno);
				
				request.setAttribute("bvo", bvo);
				destPage="/board/modify.jsp";
				
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;
		case "edit" :
			try {
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				log.info(">>> 1");
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(3*1024*1024);
				log.info(">>> 2");
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>> 3");
				List<FileItem> itemList = fileUpload.parseRequest(request);
				String old_file = null;
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
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
						//이전 그림파일의 보관용
						old_file = item.getString("utf-8");
						log.info(">>> 4");
						break;
					case "new_file":
						//새로운 파일은 등록이 될수도 있고, 안될 수도 있음.
						log.info(">>> 5");
						if(item.getSize()>0) {
							//새로운 등록파일이 있다면...
							if(old_file != null) {
								//old_file 삭제 작업
								//파일 삭제 핸들러를 통해서 파일 삭제 작업 진행
							FileRemoveHanlder fileHandler = new FileRemoveHanlder();
							isOk= fileHandler.deleteFile(old_file, savePath);
							}
							
							//새로운 파일에 대한 객체 작업
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info("new File Name>>{}" + fileName);
							
							fileName = System.currentTimeMillis()+"_" + fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							
							try {
								item.write(uploadFilePath);
								bvo.setImageFile(fileName);
								Thumbnails.of(uploadFilePath)
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
								
							} catch (Exception e) {
								log.info("File update error");
								e.printStackTrace();
							}							
							
						}else {
							bvo.setImageFile(old_file);
					}
						break;
					}
				}
				
				isOk = bsv.edit(bvo);
				log.info("edit >> {}", isOk > 0? "OK" : "Fail");
				destPage="list";
				
			} catch (Exception e) {
				log.info("edit error");
				e.printStackTrace();
			}
			break;

		case "remove" :
			  try {
			        int bno = Integer.parseInt(request.getParameter("bno"));
			        String fileName=null;
			        try {
					    fileName = request.getParameter("imagefile");
					    log.info(">>> imagefile name {} ",fileName);
					} catch (Exception e) {
						e.printStackTrace();
						
					}
			       			        
			        FileRemoveHanlder fileHandler = new FileRemoveHanlder();
			        savePath = getServletContext().getRealPath("/_fileUpload");

			  if(fileName != null) {
			    	isOk = fileHandler.deleteFile(fileName, savePath);
			        log.info(">>>deleteimg check 1: {}", isOk>0 ? "Success" : "Failed");
			  		}

		        isOk = bsv.remove(bno);
		        log.info(">>> deleteimg check 3: {}", isOk > 0 ? "Success" : "Failed");
		 
		        destPage = "list";
			  
			    } catch (Exception e) {
			        log.info(">>> Remove operation error");
			        e.printStackTrace();
			    }
		}
	
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response); 		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);
	}

}
