package kr.or.ddit.fileupload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;

// DB에 저장된 전체 파일 목록을 가져와서 View로 보내주는 Servlet
@WebServlet("/fileList.do")
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		// 전체 파일 목록 가져오기
		List<FileInfoVO> fileList = service.getAllFileinfo();
		
		// 가져온 파일 목록을 View로 전달하기 위해 저장한다.
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/basic/fileupload/fileList.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
