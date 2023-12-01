package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int firstNum = Integer.parseInt(request.getParameter("firstNum"));
		int secondNum = Integer.parseInt(request.getParameter("secondNum"));
		
		String oper = request.getParameter("oper");
		
		// 출력 부분
		PrintWriter out = response.getWriter();

		double result = operation(firstNum, secondNum, oper);
		
		out.println("<h2>계산 결과</h2><hr>");
		if ( secondNum == 0 ) {
			out.println("계산이 불가합니다.");
			return;
		}
		out.println(firstNum + " " + oper + " " + secondNum + " = " + result);
	}

	private double operation(int firstNum, int secondNum, String oper) {
		double result = 0;
		
		switch(oper) {
		case "+":
			result = firstNum + secondNum;
			break;
		case "-":
			result = firstNum - secondNum;
			break;
		case "*":
			result = firstNum * secondNum;
			break;
		case "/":
			result = firstNum / (double)secondNum;
			break;
		case "%":
			result = firstNum % secondNum;
			break;
		}
		
		return result;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
