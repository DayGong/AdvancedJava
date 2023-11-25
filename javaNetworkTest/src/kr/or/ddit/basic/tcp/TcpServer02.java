package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {
	public static void main(String[] args) {
		try {
			// ServerSocket을 생성하고 클라이언트의 접속을 기다린다.
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("서버가 준비중입니다.");
			
			Socket socket = server.accept();
			
			// 클라이언트가 접속해오면 클라이언트와 연결된 Socket객체를
			// 메시지를 받는 쓰레드와 메시지를 보내는 쓰레드에 주입한다.
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			// 쓰레드를 실행한다.
			sender.start();
			receiver.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
