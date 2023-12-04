package kr.or.ddit.json.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.json.service.ILprodService;
import kr.or.ddit.json.service.LprodServiceImpl;
import kr.or.ddit.vo.LprodVO;


@WebServlet("/lprod/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		ILprodService service = LprodServiceImpl.getInstance();
		Gson gson = new Gson();
		
		// DB에서 데이터 가져오기
		List<LprodVO> lprodList = service.getAllLprod();
		
		// DB에서 가져온 데이터를 JSON문자열로 변환
		String jsonData = gson.toJson(lprodList);
		System.out.println(jsonData);
		
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
