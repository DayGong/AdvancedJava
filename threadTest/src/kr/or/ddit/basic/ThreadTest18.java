package kr.or.ddit.basic;

/*
	wait(), notify() 메서드를 이용
	두 쓰레드가 번갈아 실행되는 예제
	
	wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능하다.
*/

public class ThreadTest18 {
	public static void main(String[] args) {
		WorkObject wObj = new WorkObject();
		
		ThreadAA thAA = new ThreadAA(wObj);
		ThreadBB thBB = new ThreadBB(wObj);
		
		thAA.start();
		thBB.start();
	}
}

// WorkObject의 methodA()메서드만 호출하는 쓰레드
class ThreadAA extends Thread {
	private WorkObject workObj;
	
	public ThreadAA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		
		synchronized (workObj) {
			workObj.notify();
		}
	}
	
}

//WorkObject의 methodB()메서드만 호출하는 쓰레드
class ThreadBB extends Thread {
	private WorkObject workObj;
	
	public ThreadBB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

// 공통으로 사용하는 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 실행 AAAAAAAAAAAAAAAAAAAAAA");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드 실행 BBBBBBBBBBBBBBBBBBBBBB");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
}