package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieAdd.do")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		/*
		 - Cookie정보를 저장하는 방법
		 1. Cookie객체를 생성한다. ==> '쿠키 이름'과 '쿠키 값'을 문자열로 셋팅한다.
		 	형식) Cookie cookie변수 = new Cookie("쿠키이름", "쿠키값")
		 	==> 쿠키값으로 한글을 사용할 경우에는 URLEncoder.encode()메서드로 인코딩한 후 저장한다.
		 	==> 하나의 Cookie는 4KB까지 저장 가능하다.
		 	==> 하나의 도메인당 20개(총 300개)까지 저장 가능하다.
		 */
		Cookie nameCookie = new Cookie("name", "홍길동");
		Cookie ageCookie = new Cookie("age", "30");
		Cookie ageCookie2 = new Cookie("age", 30 + "");
		Cookie ageCookie3 = new Cookie("age", String.valueOf(30));
		Cookie genderCookie = new Cookie("gender", "Male");
		
		/*
		 2. 쿠키 속성 설정
		 	cookie변수.setPath("적용 경로");
		 		==> 지정한 경로와 그 하위 경로에서만 사용 가능
		 		==> 생략하면 쿠키를 저장한 문서의 경로가 설정된다.
		 	cookie변수.setMaxAge(유지시간);
		 		==> 단위(초), (-1: 브라우저가 종료될 떄까지 유지(기본값), 0: 즉시 삭제된다.)
		 	cookie변수.setDomain("적용도메인명");
		 		==> 예) ".ddit.or.kr" ==> www.ddit.or.kr, mail.ddit.or.kr
		 		==> 생략되면 현재 도메인으로 설정된다.
		 	cookie변수.setSecure(보안여부); ==> true: 보안 적용, false: 보안 미적용(기본값)
		 */
		
		/*
		 3. Response객체를 이용하여 쿠키를 웹브라우저로 보내면
		 	웹브라우저가 이 쿠키를 받아서 저장한다.
		 	형식) Response객체.addCookie(1번에서 만든 Cookie객체);
		 */
		response.addCookie(nameCookie);
		response.addCookie(ageCookie);
		response.addCookie(genderCookie);
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 저장 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>Cookie데이터가 저장되었습니다.</h3><br><hr>");
		
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest01.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
