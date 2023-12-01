package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session에 데이터를 저장하는 방법
		
		// 1. Session객체를 생성하거나 현재 세션 가져오기
		// 형식1) Request객체.getSession(); 또는 Request객체.getSession(true);
		// 		==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성하여 반환한다.
		// 형식2) Request객체.getSessioin(false);
		//		==> 현재 세션이 존재하면 현재 세션을 반환하고,
		//			존재하지 않으면 새로운 세션을 생성하지 않고 null을 반환한다.
		
		HttpSession session = request.getSession();
		
		// Session에 데이터값 저장하기 ==> setAttribute()메서드 이용
		// 형식) Session객체.setAttribute("key값", 데이터값)
		//		==> 'key값'은 String, '데이터값'은 Java의 모든 자료형
		session.setAttribute("testSession", "연습용 세션입니다.");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 30);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>Session 데이터가 저장되었습니다.</h2><br><br>");
		out.println("<a href='"+ request.getContextPath() 
		+"/basic/session/sessionTest01.jsp'>시작 문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
