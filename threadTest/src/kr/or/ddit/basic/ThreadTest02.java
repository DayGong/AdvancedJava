package kr.or.ddit.basic;

public class ThreadTest02 {
	
	// 멀티 쓰레드 프로그램
	public static void main(String[] args) {
		// Thread를 사용하는 방법
		
		// 방법1)
		// Thread 클래스를 상속한 class를 작성한 후 이 클래스의 인스턴스를 생성한다.
		// 이 인스턴스의 start()메서드를 호출해서 실행한다.
		
		MyThread1 th1 = new MyThread1(); // 인스턴스 생성
		th1.start();					 // start()메서드 호출
		
		// 방법2)
		// Runnable인터페이스를 구현한 class를 작성한 후 이 클래스의 인스턴스를 생성한다.
		// 이 인스턴스를 Thread클래스의 인스턴스를 생성할 때 생성자의 인수값으로 넘겨준다.
		// 이 때 생성된 Thread클래스의 인스턴스의 start()메서드를 호출해서 실행한다.
		
		MyRunner1 runner = new MyRunner1(); // 인스턴스 생성
		Thread th2 = new Thread(runner);	// Thread클래스의 인스턴스 생성할 때 넣어준다.
		th2.start();
		
		// 방법2-1)
		// 익명 구현체를 이용하는 방법
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// @200개
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		};
		
		Thread th3 = new Thread(r);
		th3.start();
		
		System.out.println("main 메서드 끝");
	}
}

// 방법1)Thread 클래스를 상속한 class를 작성
class MyThread1 extends Thread {
	@Override
	public void run() {
		// 이 run()메서드 안에 쓰레드가 처리할 내용을 작성한다.
		// *200개
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			
			try {
				Thread.sleep(100);
				// Thread.sleep(시간);
				// 주어진 '시간'동안 잠시 멈춘다.
				// '시간'은 밀리세컨드 단위를 사용한다.(1초 == 1000)
			} catch(InterruptedException e) {
			}
		}
	}
}

// 방법2)Runnable인터페이스를 구현한 class를 작성
class MyRunner1 implements Runnable {
	@Override
	public void run() {
		// 이 run()메서드 안에 쓰레드가 처리할 내용을 작성한다.
		// $200개
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}