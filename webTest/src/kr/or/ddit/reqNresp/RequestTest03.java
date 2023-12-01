package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest03
 */
@WebServlet("/requestTest03.do")
public class RequestTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int year = Integer.parseInt(request.getParameter("year"));
		
		boolean chkLeapYear = checkYear(year);
		
		PrintWriter out = response.getWriter();
		
		out.println("<h2>윤년 확인 결과</h2><hr>");
		if (chkLeapYear == true) {
			out.println("윤년입니다.");
		} else {
			out.println("평년입니다.");
		}
	}

	private boolean checkYear(int year) {
		boolean flag = false;
		
		boolean four = (year%4 == 0);
		boolean not100 = (year%100 != 0);
		boolean four100 = (year%400 == 0);
		
		if ( (four && not100) || (four100)) {
			flag = true;
		}
		
		return flag;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
