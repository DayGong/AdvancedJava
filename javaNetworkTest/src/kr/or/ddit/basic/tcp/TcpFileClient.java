package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

/*
	접속이 완료되면 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송
*/
public class TcpFileClient {
	public void clientStart() {
		// 전송할 파일 정보를 갖는 File객체 생성
		File file = new File("d:/d_other/펭귄.jpg");
		
		String fileName = file.getName();
		if (!file.exists()) {
			System.out.println(fileName = " 파일이 없습니다.");
			System.out.println("작업을 종료합니다.");
			return;
		}
		
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작");
			
			// 파일을 읽어서 소켓으로 출력하기
			
			// 파일 입력용 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			// 서버로 전송할 스트림 객체 생성
			// 소켓의 출력 스트림에 데이터를 쓰기 위한 BufferedOutputStream를 생성
			BufferedOutputStream bout = 
					new BufferedOutputStream(socket.getOutputStream());
			
			// 파일을 읽어서 소켓으로 출력하기
			byte[] temp = new byte[1024];
			int len = 0;
			while( (len = bin.read(temp)) > 0 ) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			System.out.println("파일 전송 완료");
			
			// 스트림과 소켓 닫기
			bin.close();
			bout.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("파일 전송 실패 " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
}
