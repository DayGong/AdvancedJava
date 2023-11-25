package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer01 {
	public static void main(String[] args) throws IOException {
		// ServerSocket객체 생성
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("접속을 기다립니다.");
		
		// accept()메서드
		// 클라이언트에서 연결 요청이 올 때까지 계속 기다린다.
		// 연결 요청이 오면 새로운 Socket객체를 생성해서 클라이언트의 Socket객체와 연결한다.
		Socket socket = server.accept();
		
		// accept()메서드 호출 이후의 코드는 클라이언트와 연결이 완료된 이후의 처리 내용을 작성하면 된다.
		System.out.println();
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();
		
		
		System.out.println("==============================================");
		System.out.println("접속한 상대방 컴퓨터의 IP정보");
		System.out.println("IP 주소: " + socket.getInetAddress().getHostAddress());
		System.out.println("Port 번호: " + socket.getPort());
		System.out.println("==============================================");
		System.out.println("현재 컴퓨터의 IP정보");
		System.out.println("IP 주소: " + socket.getLocalAddress());
		System.out.println("Port 번호: " + socket.getLocalPort());
		System.out.println("==============================================");
		System.out.println();
		
		// 클라이언트로 메시지 보내기
		Scanner scan = new Scanner(System.in);
		System.out.print("클라이언트로 보낼 메시지 입력 >> ");
		String msg = scan.nextLine();
		
		// Socket객체의 OutputStream객체를 이용해서 상대방에서 데이터를 전송한다.
		// (Socket객체의 getOutputStream()메서드를 이용하여 구한다.)
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		
		// Socket객체로 만든 OutputStream으로 데이터를 출력하는 작업이 전송 작업이 된다.
		// writeUTF(String str): UTF-8 형식으로 코딩된 문자열을 출력
		dout.writeUTF(msg);
		System.out.println("메시지를 전송했습니다.");
		
		// 사용했던 Socket과 스트림 닫기
		dout.close();
		socket.close();
		server.close();
	}
}
