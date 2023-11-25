package kr.or.ddit.basic;

// yield()메서드 연습용
public class ThreadTest10 {
	public static void main(String[] args) {
		YieldThreadTest th1 = new YieldThreadTest("1번 쓰레드");
		YieldThreadTest th2 = new YieldThreadTest("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		
		System.out.println("=============11111");
		
		th1.setWork(false);
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		
		System.out.println("=============22222");
		th1.setWork(true);
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		System.out.println("=============33333");
		th1.setStop(true);
		th2.setStop(true);
	}
}

// yield()메서드 연습용 쓰레드
class YieldThreadTest extends Thread {
	private boolean stop = false;
	private boolean work = true;

	// 생성자
	public YieldThreadTest(String name) {
		super(name); // 쓰레드의 이름을 설정한다.
	}

	// getter, setter
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}

	@Override
	public void run() {
		while (!stop) {	// stop변수가 true면 반복문 종료
			if (work) {
				// getName() 메서드 ==> 현재 쓰레드의 name 속성값을 반환한다.
				System.out.println(getName() + " 작업중 ");
			} else {
				System.out.println(getName() + " 양보 ");
				Thread.yield();
			}
		}
	}

}