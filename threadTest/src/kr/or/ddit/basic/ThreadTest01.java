package kr.or.ddit.basic;

public class ThreadTest01 {
	
	// 싱글 쓰레드 프로그램
	public static void main(String[] args) {
		// *200개 $200개
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
		}
	}
}
