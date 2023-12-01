package kr.or.ddit.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.SampleVO;

@WebServlet("/json/jsonTest.do")
public class JsonTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// JSON데이터로 응답할 때의 Content-Type 설정
		response.setContentType("application/json; charset=utf-8");
		
		String choice = request.getParameter("choice");
		
		// 자바의 데이터를 JSON구조의 문자열로 변환하는 Gson객체 생성
		// 형식) Gson객체.toJson(자바의 데이터)
		// 		==> '자바의 데이터'를 JSON구조의 문자열로 변환한다.
		Gson gson = new Gson();
		
		String jsonData = null;		// JSON구조의 문자열로 변환된 데이터가 저장될 변수 선언
		
		switch(choice) {
			case "string":
				String str = "안녕하세요";
				jsonData = gson.toJson(str);
				break;
			case "array":
				int[] arr = {10, 20, 30, 40, 50};
				jsonData = gson.toJson(arr);
				break;
			case "object":
				SampleVO sample = new SampleVO(1, "홍길동", 30);
				jsonData = gson.toJson(sample);
				break;
			case "list":
				ArrayList<SampleVO> samList = new ArrayList<SampleVO>();
				samList.add(new SampleVO(100, "이순신", 40));
				samList.add(new SampleVO(200, "강감찬", 30));
				samList.add(new SampleVO(300, "일지매", 20));
				samList.add(new SampleVO(400, "성춘향", 10));
				jsonData = gson.toJson(samList);
				break;
			case "map":
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", "이순신");
				map.put("tel", "010-1234-5678");
				map.put("addr", "대전시 중구 오류동");
				jsonData = gson.toJson(map);
				break;
		}
		
		System.out.println("jsonData ==> " + jsonData);
		
		// 변환된 JSON구조의 문자열을 응답으로 전송한다.
		PrintWriter out = response.getWriter();
		
		out.write(jsonData);
		
		response.flushBuffer();	// 출력 버퍼 비우기
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
