package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirectTest02.do")
public class RedirectTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpServletRequest객체와 HttpServletResponse객체를 공유하지 않기 때문에
		// 클라이언트가 요청할 떄 보내온 파라미터들을 가져올 수 없다.
		request.setCharacterEncoding("utf-8");
		
		/*
		// Request객체를 이용하여 직접 받는 것은 불가능
		String userName = request.getParameter("username");
		String tel = (String)request.getAttribute("tel");
		
		System.out.println("userName = " + userName);
		System.out.println("tel = " + tel);
		*/
		
		// GET방식으로 지정한 데이터를 받는다.
		String userName = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Redirect방식 연습</title></head>");
		out.println("<body>");
		out.println("<h3>Forward 결과</h3>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td><td>" + userName + "</td></tr>");
		out.println("<tr><td>전화</td><td>" + tel + "</td></tr>");
		out.println("</table>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
