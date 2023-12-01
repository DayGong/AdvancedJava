package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST방식으로 전달되는 데이터의 문자 인코딩 방식 설정
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 보내온 데이터 받기
		// request객체.getParameter("파라미터명")
		// ==> 해당 '파라미터명'에 설정된 '데이터값'을 가져온다.
		// ==> 가져온 '데이터값'의 자료형은 'String'이다.
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// request객체.getParameterValues("파라미터명")
		// ==> '파라미터명'이 같은 것이 여러 개일 경우에 사용한다.
		// ==> 가져오는 '데이터값'의 자료형은 'String[]'이다.
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>request객체 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>받은 데이터 출력</h2>");
		out.println("<table border='1'>");
		
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");
		
		out.println("<tr><td>직업</td>");
		out.println("<td>" + job + "</td></tr>");
		
		out.println("<tr><td>취미</td>");
		out.println("<td>");
		
		if(hobbies == null) {
			out.println("취미가 없습니다.");
		} else {
			for (String hobby : hobbies) {
				out.println(hobby + "<br>");
			}
		}
		
		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<hr>");
		out.println("<h2>Request객체에서 제공하는 정보</h2>");
		out.println("<ul>");
		out.println("<li>클라이언트의 IP주소: " + request.getRemoteAddr() +"</li>");
		out.println("<li>요청 메서드 : " + request.getMethod() + "</li>");
		out.println("<li>Context Path: " + request.getContextPath() + "</li>");
		out.println("<li>프로토콜: " + request.getProtocol() + "</li>");
		out.println("<li>URL 정보: " + request.getRequestURL() + "</li>");
		out.println("<li>URI 정보: " + request.getRequestURI() + "</li>");
		out.println("</ul>");
		
		// request객체.getParameterNames()
		// ==> 전송된 모든 파라미터명을 Enumeration<String>객체에 담아서 반환한다.
		out.println("<h3>클라이언트가 보내온 파라미터의 이름 구하기</h3>");
		Enumeration<String> paramNames = request.getParameterNames();
		out.println("<ol>");
		
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			out.println("<li>" + paramName + "</li>");
		}
		
		out.println("</ol>");
		out.println("<hr>");
		
		// request객체.getParameterMap()
		// ==> 전송된 모든 파라미터들을 Map객체에 담아서 반환한다.
		// ==> 이 Map객체의 key값은 파라미터명이 저장되고 자료형은 String,
		// ==>			  value값은 파라미터에 저장된 '값'이 저장되고 자료형은 String[]
		out.println("<h3>getParameterMap()메서드를 이용한 파라미터값 구하기</h3>");
		Map<String, String[]> paramMap = request.getParameterMap();
		
		out.println("<table border='1'>");
		out.println("<tr><td>파라미터 이름</td><td>파라미터 value</td></tr>");
		
		Iterator<String> paramIt = paramMap.keySet().iterator();
		
		while(paramIt.hasNext()) {
			String pName = paramIt.next(); // 파라미터명 구하기
			
			out.println("<tr><td>" + pName + "</td>");
			out.println("<td>");
			
			String[] pValue = paramMap.get(pName);
			
			if(pValue == null || pValue.length == 0) {
				continue;
			} else if(pValue.length == 1) {
				out.println(pValue[0]);	// 파라미터값이 1개일 때
			} else {
				// 파라미터 값이 여러개일 때
				for (String value : pValue) {
					out.println(value + "<br>");
				}
			}
			out.println("</td></tr>");
		}
		out.println("</table>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
