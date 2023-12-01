package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// Session에 저장된 데이터 읽어오기
		
		// 1. Session 객체 구하기
		HttpSession session = request.getSession();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session연습</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Session 데이터 확인하기</h2><hr>");
		out.println("<h3>세션 데이터 1개 확인하기</h3>");
		
		// 2. Session객체의 getAttribute()메서드를 이용하여 1개의 데이터를 가져올 수 있다.
		// 형식) Session객체.getAttribute("key값");
		String sessionValue = (String)session.getAttribute("testSession");
		if (sessionValue == null) {
			out.println("<h3>'testSession'의 세션값이 없습니다.</h3>");
		} else {
			out.println("<h3>testSession의 세션값 ==> " + sessionValue + "</h3>");
		}
		
		out.println("<hr>");
	
		// 모든 세션 데이터 가져오기
		// 형식) Session객체.getAttributeNames();
		//		==> Session에 저장된 모든 'key값'들을 가져온다. (반환값 자료형: Enumeration객체)
		Enumeration<String> sessionKeys = session.getAttributeNames();
		
		out.println("<h3>전체 세션 데이터 확인하기</h3>");
		out.println("<ul>");
		
		int cnt = 0;
		while(sessionKeys.hasMoreElements()) {
			cnt++;
			String key = sessionKeys.nextElement();	// Session의 key값 구하기
			out.println("<li>" + key + " : " + session.getAttribute(key) + "</li>");
		}
		
		if(cnt == 0) {
			out.println("<li>세션 데이터가 없습니다.</li>");
		}
		
		out.println("</ul>");
		out.println("<hr>");
		
		// 세션 관련 정보 출력하기
		
		// 세션ID ==> 세션을 구분하기 위한 고유한 값(이 값이 Cookie에 저장되어 사용된다.)
		out.println("세션ID: " + session.getId() +"<br>");
		
		// 생성시간 ==> 1970년 1월 1일 0시 0분 0초부터 경과한 시간(밀리세컨드 단위)
		out.println("세션 생성 시간: " + session.getCreationTime() +"<br>");
		out.println("세션 최근 접근 시간: " + session.getLastAccessedTime() +"<br>");
		
		// 유효시간 ==> 초단위
		out.println("세션 유효 시간: " + session.getMaxInactiveInterval() +"<br>");
		out.println("<hr>");
		
		out.println("<a href='"+ request.getContextPath() 
		+"/basic/session/sessionTest01.jsp'>시작 문서로 이동하기</a>");
		
		out.println("</body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
