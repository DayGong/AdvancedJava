package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;

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
	} // doGet()메서드 끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 업로드한 파일이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		// 파일이 아닌 일반 파라미터 데이터는 
		// getParameter()메서드나 getParameterValues()메서드를 이용해서 처리할 수 있다.
		String userName = request.getParameter("username");
		System.out.println("일반 파라미터 데이터 ==> " + userName);
		
		// 수신 받은 파일 데이터를 처리하기
		String fileName = "";	// 파일명이 저장될 변수 선언
		
		// Upload한 파일 목록이 저장될 List객체 생성
		List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
		
		// 전체 Part객체 개수만큼 반복처리 한다.
		for(Part part : request.getParts()) {
			fileName = extractFileName(part);	// 파일명 구하기
			
			// 찾은 파일명이 공백("")이면 이것은 파일이 아닌 일반 파라미터라는 의미이다.
			if(!"".equals(fileName)) { // 파일인지 검사
				// 1개의 upload파일에 대한 정보를 저장할 VO객체 생성
				 FileInfoVO fvo = new FileInfoVO();
				 
				 fvo.setFile_writer(userName);		// 작성자를 VO에 저장
				 fvo.setOrigin_file_name(fileName); // 실제 파일명을 VO에 저장
				 
				 // 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 
				 // UUID클래스를 이용하여 저장할 파일명을 만든다.
				 String saveFileName = UUID.randomUUID().toString() + "_" + fileName;
				 
				 // 새롭게 만든 저장파일명을 VO에 저장
				 fvo.setSave_file_name(saveFileName);
				 
				 // getSize()메서드를 이용하여 파일의 크기를 구한다.(단위: byte)
				 // ==> byte단위의 크기를 KB단위로 변환해서 VO에 저장한다.
				 fvo.setFile_size( (long)Math.ceil(part.getSize()/1024.0) );
				 try {
					// 파일을 지정된 폴더에 저장하기
					 part.write( uploadPath + File.separator + saveFileName );
					 fileList.add(fvo); // upload된 파일 정보를 List에 추가한다.
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // if문 끝
		} // for문 끝
		
		// -----------------------------------------------------------------------
		// List에 저장된 upload 파일 정보들을 DB에 insert한다.
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		// List개수만큼 반복
		for (FileInfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		// -----------------------------------------------------------------------
		// 저장 작업이 완료되면 파일 목록을 보여주는 페이지로 이동한다.
		response.sendRedirect(request.getContextPath() + "/fileList.do");
		
	} // doPost()메서드 끝

	// Part구조 안에서 파일명을 찾는 메서드
	private String extractFileName(Part part) {
		String fileName = "";	// 반환값(찾은 파일명)이 저장될 변수명
		
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) { // filename은 소문자로
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}
}
