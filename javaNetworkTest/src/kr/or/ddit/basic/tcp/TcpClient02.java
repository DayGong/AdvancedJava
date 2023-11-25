package kr.or.ddit.basic.tcp;

import java.net.Socket;

public class TcpClient02 {
	public static void main(String[] args) {
		try {
			// Socket객체를 생성하고 서버에 연결 요청을 한다.
			Socket socket = new Socket("localhost", 7777);
			
			// 서버와 연결이 완료되면 생성된 Socket객체를
			// 메시지를 전송하는 쓰레드와 수신하는 쓰레드에 주입한다.
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			// 쓰레드 실행
			sender.start();
			receiver.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
