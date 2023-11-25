package kr.or.ddit.basic;

// 3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데 출력을 끝낸 순서대로 나타내는 프로그램

public class ThreadTest12 {
	public static void main(String[] args) {
		PrintCharacter[] pcArr = new PrintCharacter[] {
			new PrintCharacter("홍길동"),	
			new PrintCharacter("이순신"),	
			new PrintCharacter("강감찬")	
		};
		
		for (PrintCharacter pc : pcArr) {
			pc.start();
		}
		
		for (PrintCharacter pc : pcArr) {
			try {
				pc.join();
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위: " + PrintCharacter.rank);
	}
}

// A~Z까지 출력하는 쓰레드
class PrintCharacter extends Thread {
	public static String rank = "";
	private String name;
	
	// 생성자
	public PrintCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + "의 출력 문자: " + c);
			
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println(name + " 출력 끝");
		
		PrintCharacter.rank += name + " ";
	}
}