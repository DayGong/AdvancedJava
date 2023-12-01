package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();

		// 쿠키 이름: count
		Cookie[] cookieArr = request.getCookies();

		int count = 0; // 카운트 값이 저장될 변수 선언

		if (cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				String name = cookie.getName(); // 쿠키 이름 구하기
				if ("count".equals(name)) {
					count = Integer.parseInt(cookie.getValue());
				}
			}
		}
		
		count++; // 카운트 증가하기
		
		// 증가된 카운트값이 저장된 Cookie객체 생성
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		
		// 생성된 Cookie객체를 클라이언트로 보내서 저장한다.
		response.addCookie(countCookie);
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie Count 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>어서오세요. 당신은 " + count + "번째 방문입니다.</h2>");
		
		out.println("<a href='" + request.getContextPath() +
				"/cookieCountServlet.do'>카운트 증가하기</a>");
		out.println("<a href='" + request.getContextPath() +
				"/basic/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
