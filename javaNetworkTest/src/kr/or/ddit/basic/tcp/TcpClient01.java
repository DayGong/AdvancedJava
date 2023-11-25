package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소: 192.168.xxx.xx
		// 2) 지정된 IP주소: 127.0.0.1
		// 3) 원래의 컴퓨터 이름: 예) DESKTOP-COM
		// 4) 지정된 컴퓨터 이름: localhost
		System.out.println("서버에 연결 중입니다.");
		
		// 서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
		// Socket객체는 생성이 완료되면 자동으로 지정된 IP주소와 Port번호로
		// 연결 요청을 보낸다.
		Socket socket = new Socket("localhost", 7777);
		
		// Socket객체가 생성이 완료된 후의 작업은 서버와 연결이 완료된 후의
		// 처리 내용을 작성하면 된다.
		System.out.println();
		System.out.println("서버에 연결되었습니다.");
		System.out.println();
		
		// 서버에서 보낸 메시지를 받아서 출력하기
		// Socket객체를 이용하여 InputStream객체를 생성한다.
		// (Socket객체의 getInputStream()메서드 이용
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		// 스트림의 입력용 메서드를 이용해서 데이터를 수신한다.
		System.out.println("서버에서 보낸 메시지 ==> " + din.readUTF());
		System.out.println();
		
		// 소켓과 스트림 닫기
		din.close();
		socket.close();
		
	}
}
