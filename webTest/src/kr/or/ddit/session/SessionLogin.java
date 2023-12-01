package kr.or.ddit.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// id와 password 받기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		
		// 로그인 성공 여부 검사
		if ("admin".equals(id) && "1234".equals(pass)) {
			session.setAttribute("id", id);
			// 패스워드는 따로 출력하지않으므로 저장하지않음
		}
		
		response.sendRedirect(request.getContextPath() + "/basic/session/sessionLogin.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
