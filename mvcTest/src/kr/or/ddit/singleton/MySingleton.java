package kr.or.ddit.singleton;

/*
	singleton 패턴 ==> 객체가 1개만 만들어지게 하는 방법
					(외부에서 new 명령을 사용하지 못하게한다.)
	- 사용 이유
	1) 메모리 낭비 방지
	2) 데이터의 공유가 쉽다
	
*/
public class MySingleton {
	// 1번
	private static MySingleton single;
	
	// 2번
	private MySingleton() {
		System.out.println("생성자입니다.");
	}
	
	// 3번
	public static MySingleton getInstance() {
		// 1번의 변수가 null이면 현재 객체를 생성해서 1번 변수에 저장한다.
		if (single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	// 나머지는 이 클래스를 이용해서 처리할 내용들을 작성하면 된다.
	public void displayTest() {
		System.out.println("싱글톤 클래스의 메서드 호출입니다.");
	}
}
