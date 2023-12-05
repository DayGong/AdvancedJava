package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;


@WebServlet("/fileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 파일 번호를 가져온다.
		String strFileNo = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileNo);
		
		// 파일 번호를 이용하여 DB에서 해당 파일 정보를 가져온다.
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		FileInfoVO fvo = service.getFileinfo(fileNo);
		// -------------------------------------------------------------------------
		// 업로드한 파일들이 저장된 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
				
		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		// -------------------------------------------------------------------------
		// 다운로드할 파일의 File객체 생성 ==> 실제 저장한 파일명을 지정한다.
		File downFile = new File(f, fvo.getSave_file_name()); // f는 uploadPath
		
		if(downFile.exists()) {	// 업로드 폴더에 해당 파일이 있으면
			// content-Type설정
			response.setContentType("application/octet-stream; charset=utf-8");
			
			// Response의 'content-disposition'헤더의 헤더값 설정
			String headerKey = "content-disposition";
			String headerValue = "attachment; " 
					+ getEncodedFileName(request, fvo.getOrigin_file_name());
			
			response.setHeader(headerKey, headerValue);
			
			// 실제 파일 내용을 읽어와 전송 작업을 진행한다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			try {
				// 출력용(전송용) 스트림 생성 ==> Response객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());
				
				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(downFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				while((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
				
			} catch (Exception e) {
				System.out.println("입출력 오류: " + e.getMessage());
			} finally {
				if ( bin != null ) {
					try {
						bin.close();
					} catch (Exception e) {
					}
				}
				if ( bout != null ) {
					try {
						bout.close();
					} catch (Exception e) {
					}
				}
			}
		} else {
			// 파일이 없을 때
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("<h3>" + fvo.getOrigin_file_name() 
						+ "파일이 존재하지 않습니다.</h3>");
		}
		
	} // doGet()메서드 끝

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost()메서드 끝
	
	// 다운로드 파일명에 한글이 포함되어 있을 때 한글이 깨지는 것을 방지하는 메서드
	private String getEncodedFileName(HttpServletRequest request, String fileName) {
		String encodedFileName = "";	// 반환값 변수
		
		String userAgent = request.getHeader("User-Agent");
		try {
			
			// MSIE 10버전 이하의 웹브라우저
			if(userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				encodedFileName = "filename=\"" +
			 URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "\\ ") + "\"";
			} else { // 기타 모든 웹 브라우저
				encodedFileName = "filename*=UTF-8''" +
						URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
			}
		} catch (Exception e) {
			throw new RuntimeException("지원하지 않는 인코딩 방식입니다.");
		}
		
		return encodedFileName;
	}
	
	
	

}
