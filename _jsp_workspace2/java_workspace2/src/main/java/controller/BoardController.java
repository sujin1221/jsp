package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	private RequestDispatcher rdp; //페이지로 이동시켜줌
	private String destPage; //주소를 담아서 rdp에 전달
	private int isOk;
	
	private BoardService bsv;
       
    public BoardController() {
    	bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
	String uri = request.getRequestURI();
	log.info("log객체를 통한 로그 >>> "+uri);
	String path = uri.substring(uri.lastIndexOf("/")+1);
	log.info("실 요청 경로 >> {}",path);
	
	switch (path) {
	case "register":
		destPage = "/board/register.jsp";
		break;
		
	case "insert":
		try {
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			BoardVO bvo = new BoardVO(title, writer, content);
			
			isOk = bsv.insert(bvo);
			log.info("board register >>> {} ",isOk>0 ? "success":"fail");
			destPage = "/index.jsp";
		} catch (Exception e) {
		e.printStackTrace();
		log.info("insert error");
		}
		break;
		
	case "list":
		try {
			List<BoardVO> list = bsv.list();
			request.setAttribute("list", list);
			destPage = "/board/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			log.info("list error");
		}
		break;
		
	case "detail":
		try {
			int bno = Integer.parseInt(request.getParameter("bno"));
			BoardVO bvo = bsv.detail(bno);
			request.setAttribute("bvo", bvo);
			destPage = "/board/detail.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("detail error");
		}
		break;
		
	case "modify":
		try {
			int bno = Integer.parseInt(request.getParameter("bno"));
			BoardVO bvo = bsv.detail(bno);
			request.setAttribute("bvo", bvo);
			destPage = "/board/modify.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("modify error");
		}
		break;
	
	case "edit":
		try {
			int bno = Integer.parseInt(request.getParameter("bno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			BoardVO bvo = new BoardVO(bno, title, content);
			isOk = bsv.modify(bvo);
			log.info("edit >> {} ",isOk>0 ? "ok":"fail");
			destPage = "list";
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("edit error");
		}
		break;
		
	case "remove":
		try {
			int bno = Integer.parseInt(request.getParameter("bno"));
			isOk = bsv.remove(bno);
			destPage = "list";
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("remove error");
		}
		break;

	default:
		break;
	}
	
	rdp = request.getRequestDispatcher(destPage);
	rdp.forward(request, response);
	}
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
