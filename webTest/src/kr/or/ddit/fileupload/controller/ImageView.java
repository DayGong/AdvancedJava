package kr.or.ddit.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileInfoVO;


@WebServlet("/images/imageView.do")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
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
		
		// 읽어올 이미지 파일 정보를 갖는 File객체 생성
		File imgFile = new File(f, fvo.getSave_file_name());
		
		if(imgFile.exists()) {	// 해당 이미지 파일이 있으면
			// 실제 파일 내용을 읽어와 전송 작업을 진행한다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			try {
				// 출력용(전송용) 스트림 생성 ==> Response객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());
				
				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(imgFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				while((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
				
			} catch (Exception e) {
				System.out.println("이미지 오류: " + e.getMessage());
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
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
