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

import domain.BoardVO;
import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/memb/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	//rdp
	private RequestDispatcher rdp; 
	//destpage
	private String destPage;
	//isOk
	private int isOk;
	//service
	private MemberService msv; //인터페이스로 생성
	
    public MemberController() {
    	msv = new MemberServiceImpl(); 
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//모든 서비스 처리 경로
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		HttpSession ses = request.getSession();
		
		
		log.info(">>>path>>" + path);
		switch(path){
			case "join":
				//index의 /member/join 경로
				destPage="/member/join.jsp";
				break;
			case "register":
				try {
					//jsp에서 보낸 파라미터 값 받기
					String id = request.getParameter("id");
					String pwd = request.getParameter("pwd");
					String email = request.getParameter("email");
					int age = Integer.parseInt(request.getParameter("age"));
					
					MemberVO mvo = new MemberVO(id, pwd, email, age);
					log.info(">>> join check" + mvo);
					isOk = msv.register(mvo);
					log.info("join>>", (isOk>0) ? "OK":"Fail");
					destPage="/index.jsp";
				} catch (Exception e) {
					e.printStackTrace();
					log.info(">>> join error");
				}
				break;
			case "login" :
				try {
					//id,pwd를 jsp에서 전송
					//id,pwd를 객체화 하여 db로 전송
					//같은 이름의 id/pwd가 있다면 로그인 (세션객체에 값을 저장)
					//session : 모든 jsp에서 공유되는 객체
					//id가 없으면, alert창을 이용하여 로그인 정보가 없습니다. 메시지 띄우기.
					String id = request.getParameter("id");
					String pwd = request.getParameter("pwd");
					MemberVO mvo = new MemberVO(id, pwd);
					
					MemberVO loginMvo = msv.login(mvo);
					log.info(">>> login >>> {}"+ loginMvo);
					
					//로그인 객체가 있음을 의미 만약 로그인 객체가 없다면 loginMvo = null
					//가져온 로그인 객체를 세션에 저장
					if(loginMvo != null) {
						//세션객체 가져오기
						//연결된 세션 객체가 있다면 기존 객체 가져오기, 없으면 생성
						ses = request.getSession();
						ses.setAttribute("ses", loginMvo);
						ses.setMaxInactiveInterval(10*60); //로그인 유지시간(초단위로설정);
					}else {
						//로그인 객체가 없다면
						request.setAttribute("msg_login", -1);
					}
					destPage="/index.jsp";
					
				} catch (Exception e) {
					e.printStackTrace();
					log.info(">>> login error");
				}
				break;
			case "logout":
				try {
					//세션에 값이 있다면 해당 세션 연결 끊기
					 //로그인한 정보
					//lastLogin 정보 update 
					//ses에서 mvo객체로 가져오기.
					MemberVO mvo = (MemberVO) ses.getAttribute("ses");
					log.info("ses에서 추출한 mvo >> {}" , mvo);
					
					//lastLogin update
					isOk= msv.lastLogin(mvo.getId());
					log.info("lastLogin>> ", isOk > 0 ? "OK" : "Fail");
					ses.invalidate(); //세션 무효화 (세션 끊기)
					destPage = "/index.jsp";
					
				} catch (Exception e) {
					e.printStackTrace();
					log.info("logout error");
				}
				break;
			case "list":
				try {
					log.info("member check 1");
					List<MemberVO>member = msv.getMember();
					log.info("member>>>{}"+member);
					request.setAttribute("list", member);
					destPage = "/member/member.jsp";
				} catch (Exception e) {
					log.info("member error");
					e.printStackTrace();
				}
				break;
			case "detail":
			try {
				
				MemberVO mvoLogin = (MemberVO) ses.getAttribute("ses");
				String id = mvoLogin.getId();
				log.info("detail check 1");
				
				MemberVO mvo = msv.getDetail(id);;
				log.info("detail mvo>>>{}", mvo);
				request.setAttribute("mvo", mvo);
				destPage = "/member/memberDetail.jsp";
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
			case "edit":
				
				try {
				
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				String regdate = request.getParameter("regdate");
				String lastlogin = request.getParameter("lastlogin");
				
				MemberVO mvo = new MemberVO(id, pwd, email, age, regdate, lastlogin);
				isOk = msv.edit(mvo);
				
				
				ses.invalidate();
				request.setAttribute("updateCom", isOk);
				destPage = "/index.jsp";
				} catch (Exception e) {
					log.info("edit error");
					e.printStackTrace();
				}
				
				
				break;
			case "remove":
					try {
						String id = request.getParameter("id");
						log.info(id);
						isOk = msv.resign(id);
						request.setAttribute("deleteUser", isOk);
						destPage ="/memb/logout";
						
					} catch (Exception e) {
						log.info("resign error");
						e.printStackTrace();
						
					}
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
