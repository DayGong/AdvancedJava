package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 이 예제는 애노테이션(@WebServlet)을 이용해서 Servlet을 등록하여 처리하는 예제
 * 애노테이션(@WebServlet)은 servlet버전 3.0이상에서 사용할 수 있다.
 * 
 * @WebServlet애노테이션의 속성들
 * 1. name: 서블릿의 이름을 설정한다.(기본값: 빈 문자열(""))
 * 2. urlPatterns: 서블릿의 URL패턴의 목록을 설정한다.(기본값: 빈배열({}))
 * 	예) urlPatterns="/url1" 또는 urlPatterns={"/url01"} ==> 패턴이 1개일 경우
 *  예) urlPatterns={"/url01", "/url02", ...} ==> 패턴이 2개 이상일 경우
 * 3. value: urlPatterns와 동일한 기능을 한다.
 * 4. description: 주석(설명글)을 설정한다. 
 */

@WebServlet(
		description = "애노테이션을 이용한 서블릿",
		urlPatterns = {"/servletTest02.test", "/servletTest02.do"}
)
public class ServletTest02 extends HttpServlet {

	// GET방식일 때 처리
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// 처리한 내용 출력하기
		// 방법2: print() 또는 println()메서드 이용하기
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>두번째 Servlet 예지</title></head>");
		out.println("<body>");
		
		out.println("<h2 style='text-align:center; color:blue;'>안녕하세요<br>"
				+ "애노테이션을 이용한 두번째 Servlet 프로그램입니다.</h2>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
	// POST방식일 때 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
