package kr.or.ddit.basic;

public class EnumTest {
	public enum Color {
		RED, GREEN, BLUE
	}

	public enum Count {
		ONE, TWO, THREE
	}

	// 계절을 나타내는 상수 작성하기(설정할 값은 기간과 평균온도)
	public enum Season {
		봄("3월부터 5월까지", 13), 여름("6월부터 8월까지", 28), 가을("9월부터 11월까지", 15), 겨울("12월부터 2월까지", 1);

		// 값들이 저장될 변수 선언
		private String span;
		private int temp;

		// 생성자
		Season(String span, int temp) { // private Season(String span, int temp)와 같다
			this.span = span;
			this.temp = temp;
		}
		
		// getter 메서드 작성
		public String getSpan() {
			return span;
		}
		
		public int getTemp() {
			return temp;
		}
	}

	public static void main(String[] args) {
		/*
		 * System.out.println("Red: " + ConstTest.RED); System.out.println("Three: " +
		 * ConstTest.THREE);
		 * 
		 * if(ConstTest.RED == ConstTest.TWO) { System.out.println("true"); } else {
		 * System.out.println("false"); }
		 */

		Color mycol = Color.valueOf("GREEN"); // Color.Green과 같다.
		Count mycnt = Count.ONE; // Count.valueOf("ONE")과 같다.

		System.out.println("mycol: " + mycol.name());
		System.out.println("mycnt: " + mycnt.name());
		System.out.println();

		System.out.println("mycol ordinal: " + mycol.ordinal());
		System.out.println("mycnt ordinal: " + mycnt.ordinal());

//		if (Color.RED == Count.THREE) { // 서로 다른 종류의 열거형끼리의 비교는 불가능하다.
//		}

		if (mycol == Color.GREEN) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		// 열거형을 switch문으로 비교하기
		switch (mycnt) {
		case ONE:
			System.out.println("ONE");
			break;
		case TWO:
			System.out.println("TWO");
			break;
		case THREE:
			System.out.println("THREE");
			break;
		}
		
		System.out.println("=============================================");
		
		Season ss = Season.valueOf("봄");
		System.out.println("name: " + ss.name());
		System.out.println("ordinal: " + ss.ordinal());
		System.out.println("span: " + ss.getSpan());
		System.out.println("temp: " + ss.getTemp());
		System.out.println();
		
		// Season 열거형의 전체 상수 정보 출력하기
		for (Season time : Season.values()) {
			System.out.println(time.name() + " == " + time + 
					" ==> " + time.getSpan() + ", " + time.getTemp());
		}
	}
}
