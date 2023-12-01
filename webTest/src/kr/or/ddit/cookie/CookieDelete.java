package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 쿠키 정보를 삭제하는 서블릿
@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 저장된 쿠키 삭제하기
		//		==> 쿠키 데이터의 삭제는 해당 쿠키의 유지시간을 0으로 설정하는 방법을 사용한다.
		// 1. 전체 쿠키 정보 가져오기
		Cookie[] cookieArr = request.getCookies();
		
		if(cookieArr != null) {
			for(Cookie cookie : cookieArr) {
				// 2. 쿠키 배열에서 삭제할 쿠키 정보를 찾는다.
				String name = cookie.getName();	// '쿠키 이름' 구하기
				if ("gender".equals(name)) {
					// 3. 찾은 쿠키의 유지시간을 0으로 설정한 후 다시 저장한다.
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 삭제 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>저장된 Cookie데이터 삭제하기</h3><br>");
		
		out.println("<a href='" + request.getContextPath() 
		+ "/basic/cookie/cookieTest01.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
