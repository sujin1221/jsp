package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import domain.MemberVO;
import service.CommentService;
import service.CommentServiceImpl;

@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CommentController.class);
	private CommentService csv;
	private int isOk;
       
    public CommentController() {
      csv = new CommentServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    //contentType은 jsp 화면으로 갈때 설정 (비동기에서는 하지않음)
	    String uri = request.getRequestURI(); // /cmt/post, /cmt/list/370
	    log.info("경로 >>> {} "+uri);
	    String pathUri = uri.substring("/cmt/".length()); //post, list/370
	    String path = pathUri;
	    String pathVar = "";
	    if(pathUri.contains("/")) {
	    	path = pathUri.substring(0,pathUri.lastIndexOf("/")); //list
	    	pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1); //370
	    }
	    log.info("path >>> {} "+path);
	    log.info("pathVar >>> {} "+pathVar);
	    
	    switch (path) {
	    case "post":
	    	try {
				//js에서 보낸 데이터를 읽어들이는 작업
	    		//js -> controller string
	    		StringBuffer sb = new StringBuffer();
	    		String line = "";
	    		BufferedReader br = request.getReader(); //댓글 객체
	    		while((line = br.readLine()) != null){
	    			sb.append(line);
	    		}
	    		log.info(">>>>> sb >> {} "+sb.toString());
	    		//객체 생성
	    		JSONParser parser = new JSONParser();
	    		JSONObject jsonObj = (JSONObject)parser.parse(sb.toString()); //key: value
	    		//key를 이용해서 value 추출
	    		int bno = Integer.parseInt(jsonObj.get("bno").toString());
	    		String writer = jsonObj.get("writer").toString();
	    		String content = jsonObj.get("content").toString();
	    		
	    		CommentVO cvo = new CommentVO(bno, writer, content);
	    		log.info("CommentVO >>> {} "+cvo);
	    		isOk = csv.post(cvo);
	    		log.info("isOk >>> "+((isOk>0) ? "ok":"fail"));
	    		//결과 데이터 전송 => 화면에 출력 (response 객체의 body에 기록)
	    		PrintWriter out = response.getWriter();
	    		out.print(isOk);
			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>> comment post error...");
			}
	    	break;
	    	
	    case "list":
	    	try {
				int bno = Integer.parseInt(pathVar);
				List<CommentVO> list = csv.getList(bno);
				log.info("comment list >>> {} "+list);
				//list => json으로 변환
				//{..}, {..}, {..} ...
				JSONObject[] jsonObjArr = new JSONObject[list.size()]; 
			    JSONArray jsonObjList = new JSONArray(); //[{..}, {..}, {..}]
			    for(int i=0;i<list.size();i++) {
			    	jsonObjArr[i] = new JSONObject();
			    	jsonObjArr[i].put("cno", list.get(i).getCno());
			    	jsonObjArr[i].put("bno", list.get(i).getBno());
			    	jsonObjArr[i].put("writer", list.get(i).getWriter());
			    	jsonObjArr[i].put("content", list.get(i).getContent());
			    	jsonObjArr[i].put("regdate", list.get(i).getRegdate());
			    	
			    	jsonObjList.add(jsonObjArr[i]);
			    }
			    //'{..}, {..}, {..}'
			    String jsonData = jsonObjList.toJSONString(); //string으로 변경해야 전송 가능함
			    PrintWriter out = response.getWriter();
			    out.print(jsonData);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("comment list error");
			}
	    	break;
	    	
	    case "remove":
	    	try {
				int cno = Integer.parseInt(request.getParameter("cnoVal"));
				isOk = csv.remove(cno);
				log.info(">>>> comment remove check 1");
				log.info("remove >>> {} "+ ((isOk>0)? "OK":"FAIL"));
				PrintWriter out = response.getWriter();
				out.print(isOk);
				log.info("comment remove check 4");
			} catch (Exception e) {
				e.printStackTrace();
				log.info("comment remove error");
			}
	    	break;	
	    	
	    case "modify":
	    	try {
//	    		HttpSession ses = request.getSession();
//	    		MemberVO mvo = (MemberVO)ses.getAttribute("ses"); 		
	    		StringBuffer sb = new StringBuffer();
	    		String line = "";
	    		BufferedReader br = request.getReader(); //댓글 객체
	    		while((line = br.readLine()) != null){
	    			sb.append(line);
	    		}
	    		log.info(">>>>> sb >> {} "+sb.toString());
	    		//객체 생성
	    		JSONParser parser = new JSONParser();
	    		JSONObject jsonObj = (JSONObject)parser.parse(sb.toString()); //key: value
	    		//key를 이용해서 value 추출
	    		int cno = Integer.parseInt(jsonObj.get("cno").toString());
	    		String content = jsonObj.get("content").toString();	    	    		
	    		CommentVO cvo = new CommentVO(cno, content);
	    		log.info("CommentVO >>> {} "+cvo);
	    		isOk = csv.modify(cvo);
	    		log.info("isOk >>> "+((isOk>0) ? "ok":"fail"));
	    		//결과 데이터 전송 => 화면에 출력 (response 객체의 body에 기록)
	    		PrintWriter out = response.getWriter();
	    		out.print(isOk);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("comment modify error");
			}
	    	break;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
