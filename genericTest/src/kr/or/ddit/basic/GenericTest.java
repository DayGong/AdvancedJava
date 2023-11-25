package kr.or.ddit.basic;

// 제네릭을 사용하지 않은 경우
class NonGenericTest {
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

// 제네릭을 사용한 경우
class MyGeneric<T> {
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}

public class GenericTest {
	public static void main(String[] args) {
		NonGenericTest ng1 = new NonGenericTest();
		ng1.setValue("가나다라");
		
		NonGenericTest ng2 = new NonGenericTest();
		ng2.setValue(100);
		
		String strTemp = (String)ng1.getValue();
		System.out.println("문자열 반환값: " + strTemp);
		
		int iTemp = (int)ng2.getValue();
		System.out.println("정수형 반환값: " + iTemp);
		
		System.out.println("--------------------------------------");
		
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setValue("우리나라");
//		mg1.setValue(500); // 제네릭으로 지정한 자료형과 다른 자료형은 사용 불가
		
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setValue(500);
//		mg2.setValue("우리나라"); // 오류
		
		strTemp = mg1.getValue();
		iTemp = mg2.getValue();
		
		System.out.println("제네릭 - 문자열 반환값: " + strTemp);
		System.out.println("제네릭 - 정수형 반환값: " + iTemp);
				
		System.out.println("--------------------------------------");
		
		NonGenericTest ng3 = new NonGenericTest();
		ng3.setValue("ABCD");
		
		int test = (int)ng3.getValue(); // 제네릭이 만들어진 이유
		System.out.println(test);
	}
}
