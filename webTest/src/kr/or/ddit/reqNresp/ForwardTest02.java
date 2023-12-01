package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForwardTest02
 */
@WebServlet("/forwardTest02.do")
public class ForwardTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpServletRequest객체와 HttpServletResponse객체를 공유하기 때문에
		// 클라이언트가 요청할 때 보내온 파라미터들을 가져올 수 있다.
		request.setCharacterEncoding("utf-8");

		String userName = request.getParameter("username"); // 파라미터 정보 가져오기
		
		// setAttribute()메서드로 저장한 데이터 가져오기
		// setAttribute()으로 가져온 타입은 Object기 때문에 형변환 필요
		String tel = (String)request.getAttribute("tel");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>forward방식 연습</title></head>");
		out.println("<body>");
		out.println("<h3>Forward 결과</h3>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td><td>" + userName + "</td></tr>");
		out.println("<tr><td>전화</td><td>" + tel + "</td></tr>");
		out.println("</table>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
