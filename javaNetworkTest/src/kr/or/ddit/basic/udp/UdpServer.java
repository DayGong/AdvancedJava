package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {
	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성
			DatagramSocket socket = new DatagramSocket(8888);
			
			System.out.println("서버 실행 중");
			
			while(true) {
				// 수신받은 메시지가 저장될 변수 선언
				byte[] inMsg = new byte[1024];
				
				// 수신용 패킷객체 생성
				// ==> 생성자에 '데이터가 저장될 byte형 배열'과 '이 배열의 크기'를
				// 	   지정하여 생성한다.
				DatagramPacket inpacket = new DatagramPacket(inMsg, inMsg.length);
				
				// 데이터 수신하기 ==> receive() 메서드를 이용한다.
				// 이 메서드는 데이터가 올 때까지 기다린다.
				
				// 수신된 데이터의 패킷 정보가 지정한 패킷변수(inpacket)에 저장된다.
				socket.receive(inpacket);
				
				// 수신 받은 패킷에서 상대방의 주소, 포트번호 등을 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP정보: " + address);
				System.out.println("상대방의 Port정보: " + port);
				System.out.println();
				
				// 상대방이 보낸 메시지 출력하기
				// 수신용패킷객체변수.getLength() ==> 실제 읽어온 데이터의 길이를 반환한다.
				// 수신용패킷객체변수.getData()   ==> 실제 읽어온 데이터를 byte배열로 반환한다.
				// 수신된 데이터는 수신용 패킷객체를 생성할 때 지정한 byte형 배열에도 같이 저장된다.
				
				// byte배열에서 0번째부터 길이만큼 데이터를 꺼내와라
//				String msg = new String(inMsg, 0, inpacket.getLength(), "utf-8");
				String msg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8");
				
				if("/end".equals(msg)) { // 작업 종료(반복문 탈출)
					break;
				}
				
				System.out.println("상대방이 보낸 메시지: " + msg);
				System.out.println();
				
				// -------------------------------------------------------------
				// 상대방에게 메시지 보내기(수신받은 메시지를 그대로 송신하기)
				
				// 송신할 메시지를 byte형 배열로 변환한다.
				byte[] sendMsg = msg.getBytes("utf-8");
				
				// 송신용 패킷 객체 생성하기
				// ==> 생성자에 '전송할 데이터가 저장된 byte형 배열', '전송할 데이터의 길이(배열의 길이)',
				// '상대방주소정보', '상대방의 포트번호'를 지정하여 생성한다.
				DatagramPacket outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 데이터 전송하기 ==> send()메서드를 이용한다.
				// send()메서드에 송신용 패킷객체를 지정하여 호출한다.
				socket.send(outpacket);
				System.out.println("송신완료");
				System.out.println();
			}
			
			System.out.println("통신 끝");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
