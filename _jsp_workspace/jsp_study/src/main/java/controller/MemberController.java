package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/memb/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	//rdp => 요청에 대한 응답을 실행
	private RequestDispatcher rdp;
	//destpage
    private String destpage;
	//isOk
    private int isOk;
	//service
    private MemberService msv; //'interface'로 생성해야함
       
    public MemberController() {
       msv = new MemberServiceImpl(); //service class로 생성해야함
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//모든 서비스 처리 경로
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);		
		log.info(">>> path >> "+path);
		
		switch(path) {
		case "join":
			//index의 /member/join 경로...
			destpage = "/member/join.jsp";
			break;
			
		case "register":
			try {
				//jsp에서 보낸 파라미터 값 받기
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				
				MemberVO mvo = new MemberVO(id, pwd, email, age);
				log.info(">>> join check 1 "+mvo);
				isOk = msv.register(mvo);
				log.info("join >> ", (isOk>0)? "ok":"fail");
				destpage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>> join error");
			}
			break;
			
		case "login":
			//id,pwd를 jsp에서 전송
            //id,pwd를 객체화 하여 db로 전송
            //같은 이름의 id/pwd가 있다면 로그인 (세션객체에 값을 저장)
            //session : 모든 jsp에서 공유되는 객체
            //id가 없으면, alert창을 이용하여 로그인 정보가 없습니다. 메시지 띄우기.
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				MemberVO Mvo = new MemberVO(id,pwd);
				
				MemberVO loginMVO = msv.login(Mvo); 
				if(loginMVO != null) {
					HttpSession ses = request.getSession();
					 ses.setAttribute("ses", loginMVO);
	                  ses.setMaxInactiveInterval(10*60); //로그인 유지시간(초단위 설정)
				  }else {
	                  //로그인 객체가 없다면
	                  request.setAttribute("msg_login", -1);
	               }
				log.info(">>> login check 4");
				destpage="/index.jsp";
			} catch (Exception e) {
				log.info("login error");
				e.printStackTrace();
			}
			break;			
		case "logout":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				log.info("logout check1");
				isOk = msv.lastLogin(id);
				ses.invalidate();
				log.info("logout check4");
				destpage = "/index.jsp";
			} catch (Exception e) {
				log.info("logout error");
				e.printStackTrace();
			}
			break;	
		case "list":
			try {
				List<MemberVO> list = msv.getList();
				request.setAttribute("list", list);
				destpage = "/member/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>> list error");
			}
			break;
			
		case "detail":
			try {
				destpage = "/member/detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				log.info(">>> detail error");
			}		
			break;
				
		case "edit":
		    try {
		        String id = request.getParameter("id");
		        String pwd = request.getParameter("pwd");
		        String email = request.getParameter("email");
		        int age = Integer.parseInt(request.getParameter("age"));

		        MemberVO mvo = new MemberVO(id, pwd, email, age);
		        log.info("update check 1");
		        isOk = msv.update(mvo);
		        log.info("update >>> {} ", (isOk > 0) ? "ok" : "fail");

		        if (isOk > 0) {
		            request.setAttribute("message", "개인정보 수정에 성공했습니다.");
		        } else {		           
		            request.setAttribute("message", "개인정보 수정에 실패했습니다.");
		        }
		        destpage = "/memb/logout";
		    } catch (Exception e) {
		        log.info("update error");
		        e.printStackTrace();
		    }
		    break;
			
		case "remove":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses"); //ses라는 속성을 통해 값을 가져옴
				String id = mvo.getId(); //회원의 id를 가져옴
				isOk = msv.remove(id); //지워버리기
				ses.invalidate(); //객체 무효화
				destpage = "/index.jsp"; //index 페이지로 이동
			} catch (Exception e) {
				e.printStackTrace();
				log.info("remove error"); //에러발생시 메세지 출력
			}
			break;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destpage);
		dispatcher.forward(request, response); 
	
	}
//		//목적지 주소 값 설정
//		rdp = request.getRequestDispatcher(destpage);
//	    //목적지 주소로 전송(정보 실어서 보내기)
//		rdp.forward(request, response);	
//		
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
