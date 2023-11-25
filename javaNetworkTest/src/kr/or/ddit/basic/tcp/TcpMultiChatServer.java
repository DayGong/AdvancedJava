package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 접속한 클라이언트 정보가 저장될 Map객체
	// ==> key값: 접속한 사람 이름, value값: 접속한 클라이언트와 연결된 Socket객체
	private Map<String, Socket> clientMap;
	
	public TcpMultiChatServer() {
		// Map객체 생성 ==> 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String,	Socket>());
	}
	
	public static void main(String[] args) {
		new TcpMultiChatServer().startServer();
	}
	
	// 서버의 시작 메서드
	public void startServer() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = server.accept();	// 클라이언트의 접속을 기다린다.
				
				System.out.println("[" + socket.getInetAddress() +
						" : " + socket.getPort() + "]에서 접속");
				System.out.println();
				
				// 쓰레드 실행
				ServerReceiver th = new ServerReceiver(socket);
				th.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// clientMap변수에 저장된 전체 클라이언트들에게 메시지를 전송하는 메서드
	public void sendToAll(String msg) {
		// clientMap의 데이터 개수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				// clientMap.get(name)은 socket객체
				// key값에 대응하는 Socket객체의 출력용 스트림을 이용하여 객체를 생성한다.
				DataOutputStream dout = new DataOutputStream(
						clientMap.get(name).getOutputStream());
				
				dout.writeUTF(msg);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	// 서버에서 클라이언트가 접속해 오면 해당 클라이언트를 목록에 추가하고
	// 해당 클라이언트가 보내온 메시지를 받아서 전체 클라이언트에게 보내는 Thread 클래스
	// (Inner class - 클래스 안의 클래스로 작성한다.)
	// Inner class를 사용하는 목적 ==> Outer class의 멤버들을 자유롭게 사용할 수 있다.
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		
		// 생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 수신용 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream());
				
				// 송신용 스트림 객체 생성
				dout = new DataOutputStream(this.socket.getOutputStream());
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		@Override
		public void run() {
			// 클라이언트가 연결이 완료되면 첫번째로 '대화명'을 입력받아서 서버로 전송한다.
			// 서버에서는 이 '대화명'이 중복되는지 여부를 검사해서 그 결과를 
			// 응답으로 클라이언트에게 보낸다.
			String name = "";
			
			try {
				// 클라이언트가 보내온 '대화명'이 중복되지 않을 때까지 반복
				while(true) {
					name = din.readUTF();	// 클라이언트가 보내온 '대화명' 받기
					
					if(clientMap.containsKey(name)) {	// '대화명'이 중복될 때
						dout.writeUTF("대화명 중복");
					} else {	// '대화명'이 중복되지 않을 때
						dout.writeUTF("OK");
						break;
					}
				}
				
				// 이미 접속해 있던 사람들에게 새로운 접속자를 알려주는 메시지를 전송
				sendToAll("[" + name + "]님이 대화방에 입장했습니다.");
				
				// 대화명과 접속한 클라이언트의 Socket객체를 Map에 추가한다.
				clientMap.put(name, this.socket);
				
				System.out.println("현재 접속자 수: " + clientMap.size());
				
				// 한 클라이언트가 보내온 메시지를 받아서 전체 클라이언트들에게 보낸다.
				while(din != null) {
					sendToAll(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// 이 finally영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다.
				sendToAll("[" + name + "]님이 접속을 종료했습니다.");
				
				// 목록에서 삭제한다.
				clientMap.remove(name);
				
				System.out.println("[" + this.socket.getInetAddress() + ": "
						+ this.socket.getPort() + "]에서 접속을 종료했습니다.");
				System.out.println("현재 접속자 수: " + clientMap.size());
				System.out.println();
			}
		}
	}
}
