package kr.or.ddit.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// userid, pass, idchk 파라미터 값 구하기
		String userid = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String idchk = request.getParameter("idchk");	// checkbox 값
		
		// 일단 userid 값을 저장하는 쿠키 객체 생성
		Cookie idCookie = new Cookie("USERID", userid); 
		
		// 체크박스의 체크 여부에 따라 쿠키를 저장하거나 삭제한다.
		if(idchk == null) { // 체크박스가 되지않았을 때 (==> 쿠키 삭제)
			idCookie.setMaxAge(0);
		}
		
		response.addCookie(idCookie);
		
		// 로그인 성공 여부 검사
		if("test".equals(userid) && "1234".equals(pass)) { // 로그인 성공
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieMain.jsp");
		} else { // 로그인 실패
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieLogin.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
