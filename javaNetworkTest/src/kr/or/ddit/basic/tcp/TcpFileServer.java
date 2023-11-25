package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
	접속이 완료되면 클라이언트가 보내온 파일을 받아서
	'd:/d_other/uploadFiles'폴더에 '전송파일_펭귄.jpg'로 저장
*/
public class TcpFileServer {
	
	private void serverStart() {
		// 저장할 폴더 정보를 갖는 File객체 생성
		File saveDir = new File("d:/d_other/uploadFiles");
		
		// 저장할 폴더가 없으면 해당 폴더를 생성한다.
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		try {
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("서버가 준비되었습니다.");
			
			// 클라이언트의 요청 기다리기
			Socket socket = server.accept();
			
			// 소켓으로 읽어서 파일로 출력하기
			
			// 소켓의 입력용 스트림 객체 생성
			BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
			
			// 파일 출력용 스트림 객체 생성
			File saveFile = new File(saveDir, "전송파일_펭귄.jpg");
			BufferedOutputStream bout = 
					new BufferedOutputStream(
					new FileOutputStream(saveFile));
			
			System.out.println("파일 수신 시작");
			
			// 소켓으로 읽어서 파일로 출력하기
			byte[] temp = new byte[1024];
			int len = 0;
			
			while( (len = bin.read(temp)) > 0 ) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("파일 수신 완료");
			
			// 스트림과 소켓 닫기
			bin.close();
			bout.close();
			socket.close();
			
		} catch (Exception e) {
			System.out.println("파일 수신 실패 " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}

}
