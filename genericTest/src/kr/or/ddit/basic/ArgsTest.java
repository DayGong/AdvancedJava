package kr.or.ddit.basic;

public class ArgsTest {

	// 매개 변수로 받은 정수들의 합계를 구해서 반환하는 메서드
	// (이 정수들의 개수는 상황에 따라 다를 수 있다.)

	// 배열을 이용하여 처리하는 메서드
	public int sumArr(int[] data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		return sum;
	}

	// 가변형 인수를 이용한 메서드
	public int sumArgs(int... data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		return sum;
	}

	// 가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	public String sumArgs2(String name, int... data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		return name + "씨의 합계: " + sum;
	}

//	public void test(int a) {
//		
//	}

	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();

		int[] nums = { 100, 200, 300 }; // 배열 선언과 초기화를 같이 할 때만 선언 가능

//		int[] num2;
//		num2 = new int[] {1, 2, 3, 4, 5};

		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] { 1, 2, 3, 4, 5 }));
//		int k = 200;
//		test.test(100);

		System.out.println();
		System.out.println(test.sumArgs(100, 200, 300));
		System.out.println(test.sumArgs(1, 2, 3, 4, 5));
		
		System.out.println();
		System.out.println(test.sumArgs2("홍길동", 1, 2, 3, 4, 5));
	}
}
