package kr.or.ddit.basic;

public class ThreadTest03 {
	public static void main(String[] args) {
		// 쓰레드가 수행되는 시간을 체크
		Thread th = new Thread(new MyRunner2());
		
		// System.currentTimeMillis()
		// 1970년 1월 1일 0시 0분 0초(표준시간)부터 현재 시간까지의 경과한 시간을 밀리세컨드 단위로 반환
		// 예) 현재 시간이 1970년 1월 1일 0시 2분 20초 => 140 * 1000 ==> 140000
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join(); // 현재 위치에서 대상이 되는  쓰레드(현재는 변수 th가 가리키는 쓰레드)가
					   // 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간: " + (endTime - startTime));
		
	}
}

class MyRunner2 implements Runnable {
	@Override
	public void run() {
		long sum = 0L;
		
		for (long i = 1L; i < 2_000_000_000L; i++) {
			sum += i;
		}
		
		System.out.println("합계: " + sum);
	}
}