package kr.or.ddit.session.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.session.service.IMemberService;
import kr.or.ddit.session.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/sessionLoginDb.do")
public class SessionLoginDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// id, password 가져오기
		String userId = request.getParameter("userid");
		String pass = request.getParameter("pass");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		// 가져온 id와 password를 MemberVO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(userId);
		memVo.setMem_pass(pass);
		
		// DB에 id와 password를 보내서 해당 조건에 맞는 회원 정보를 가져온다.
		// 해당 조건에 맞지않으면 null값이 반환된다.
		MemberVO loginMemberVo = service.getLoginMember(memVo);
		
		HttpSession session = request.getSession();
		
		if(loginMemberVo != null) { // 로그인 성공
			session.setAttribute("loginMember", loginMemberVo);
		}
		
		response.sendRedirect(request.getContextPath()
				+"/basic/session/db/sessionLoginDb.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
