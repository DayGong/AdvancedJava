package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 저장된 쿠키를 읽어오는 서블릿
@WebServlet("/cookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 저장된 쿠키정보 읽어오기
		// 1. 전체 쿠키 정보를 Request객체를 통해서 가져온다.
		//		==> 가져온 쿠키 정보들은 배열에 저장된다.
		// 형식) Cookie[] 쿠키배열변수 = Request객체.getCookies();
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>쿠키 읽기 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>저장된 Cookie데이터 확인하기</h3><br>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>저장된 Cookie가 없습니다.</h3>");
		} else {
			// 2. 배열에서 해당 쿠키 정보를 구해온다.
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName();	// '쿠키이름' 가져오기
				String value = cookie.getValue(); // '쿠키값' 가져오기
				
				out.println("쿠키이름: " + name + "<br>");
				out.println("쿠키 값: " + value + "<hr>");
			}
		}
		
		out.println("<a href='" + request.getContextPath() 
			+ "/basic/cookie/cookieTest01.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
