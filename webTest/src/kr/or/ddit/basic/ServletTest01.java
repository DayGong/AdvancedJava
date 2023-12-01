package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 배포 서술자(DD, Deployment descriptor --> web.xml)를 이용해서
 * 실행할 Servlet을 등록하여 처리하는 예제
 */

/*
 * http://localhost:80/webTest/servletTest01.do
 * 
 *  - http ==> 프로토콜
 *  - localhost ==> 서버 컴퓨터 이름(도메인명) 또는 IP주소
 *  - 80 ==> 포트번호(웹에서 80번은 생략 가능)
 *  - /webTest ==> 컨텍스트 패스(보통 프로젝트 이름으로 지정된다.)
 *  - /servletTest01.do ==> 서블릿 요청의 URL 패턴
 */

public class ServletTest01 extends HttpServlet{
	// 이 영역에서는 대부분 service()메서드 또는 doGet()메서드, doPost()메서드를
	// 재정의해서 작성한다.
	
	// doGet()메서드나 doPost()메서드는 service()메서드를 통해서 호출된다.
	// 이들 메서드의 매개변수
	// - HttpServletRequest객체 ==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// - HttpServletResponse객체 ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); // 응답 문서의 인코딩 방식 지정
		response.setContentType("text/html; charset=UTF-8"); // 응답 문서의 ContentType 지정
		
		// 처리한 내용을 응답으로 보내기 위해서 PrintWriter객체를 생성한다.
		// (response객체를 이용하여 생성한다.)
		PrintWriter out = response.getWriter();
		
		// 처리한 내용을 출력한다. (클라이언트로 전송한다.)
		// 방법 1: append()메서드 이용하기
		out.append("<html>")
			.append("<head>")
			.append("<meta charset='utf-8'>")
			.append("<title>첫번째 Servlet 연습</title>")
			.append("</head>")
			.append("<body>")
			.append("<h2>안녕하세요.<br>첫번째 Servlet 프로그램입니다.</h2>")
			.append("</body>")
			.append("</html>");
		
	}
	
	// doPost()메서드 ==> POST방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
