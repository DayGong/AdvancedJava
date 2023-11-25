package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		try {
			// 소켓 객체 생성
			DatagramSocket socket = new DatagramSocket();
			
			// 상대방의 주소 정보 생성하기
//			InetAddress address = InetAddress.getByName("localhost");
			InetAddress address = InetAddress.getByName("127.0.0.1");
			
			while(true) {
				// 전송할 메시지 입력
				System.out.print("보낼 메시지 입력 >> ");
				String msg = scan.nextLine();
				
				// 송신용 패킷 객체 생성
				DatagramPacket outpacket = 
						new DatagramPacket(msg.getBytes("utf-8"), msg.getBytes("utf-8").length,
								address, 8888);
				
				// 전송하기
				socket.send(outpacket);
				
				if("/end".equals(msg)) break; // 작업 종료
				
				// -------------------------------------------------------
				System.out.println();
				
				// 상대방이 보낸 메시지 받기
				byte[] inMsg = new byte[1024];
				
				// 수신용 패킷 객체 생성
				DatagramPacket inpacket = new DatagramPacket(inMsg, inMsg.length);
				
				// 데이터 수신하기
				socket.receive(inpacket);
				
				String receiveMsg = 
						new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8");
				
				System.out.println("서버의 응답 메시지: " + receiveMsg);
				System.out.println();
				
			}
			
			System.out.println("통신 끝");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
