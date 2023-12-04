package kr.or.ddit.fileupload.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	- Servlet 3.0이상에서 파일 업로드를 하려면 서블릿에 @MultipartConfig
 	애노테이션을 설정해야 한다.
 	
 	- @MultipartConfig 애노테이션에서 설정할 값들
 	1) location: 업로드한 파일이 임시적으로 저장될 경로 지정(기본값: "" ==> 시스템의 기본 임시폴더)
 	2) fileSizeThreshold: 이곳에 지정한 값보다 큰 파일이 전송되면 location에 지정한 임시 디렉토리에 저장한다.
 						(단위: byte, 기본값: 0(무조건 임시 디렉토리 사용))
	3) maxFileSize: 1개 파일의 최대 크기(단위: byte, 기본값: -1L(무제한))
	4) maxRequestSize: 서버로 전송되는 Request의 전체 데이터의 크기
 */
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 20,	// 20MB
	maxFileSize = 1024 * 1024 * 30,			// 30MB
	maxRequestSize = 1024 * 1024 * 100		// 100MB
)
@WebServlet("/fileUpload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET방식으로 요청하면 fileUploadForm.jsp로 forwarding한다.
		request.getRequestDispatcher("/basic/fileupload/fileUploadForm.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
