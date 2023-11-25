package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	// Collection에는 객체만 저장할 수 있음

	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector();

		System.out.println("처음 크기: " + v1.size());

		// 데이터 추가하기 1 ==> add(추가할 데이터)
		// ==> 반환값: 추가 성공(true), 추가 실패(false)
		v1.add("AAAA");
		v1.add(new Integer(111)); // 자바버전 9미만에서 사용
		v1.add(Integer.valueOf(200)); // 자바버전 9이상에서 사용
		v1.add(300); // 오토 박싱, 오토 언박싱을 지원
		v1.add('a');
		v1.add(true);

		boolean r = v1.add(3.14);

		System.out.println("반환값 확인: " + r);
		System.out.println("현재 크기: " + v1.size());

		// 데이터 추가하기 2 ==> addElement(추가할 데이터)
		// ==> 이전 버전의 프로그램과 호환성을 위해서 남아 있는 메서드
		v1.addElement("BBBB");
		
		System.out.println("v1 => " + v1.toString());
		System.out.println("v1 => " + v1); // toString() 생략 가능
		
		// 데이터 추가하기 3 ==> add(index, 데이터)
		//			  ==> 'index'번째에 '데이터'를 끼워 넣는다.(index는 0부터 시작)
		//            ==> 반환값은 없다.
		v1.add(1, "KKKK");
		
		System.out.println("v1 => " + v1);
		
		// 데이터 꺼내오기 ==> get(index)
		//          ==> 'index'번째 데이터를 꺼내서 반환
		System.out.println("0번째 데이터: " + v1.get(0));
		
		int iTemp = (int)v1.get(3);
		System.out.println("3번째 데이터 : " + iTemp);
		
		// 데이터 수정하기 ==> set(index, 새로운 데이터)
		//          ==> 'index'번째의 데이터를 '새로운 데이터'로 변경
		//          ==> 반환값은 변경되기 전의 '원래의 데이터'가 반환됨
		String sTemp = (String)v1.set(0, "ZZZZ");
		System.out.println("원래의 데이터: " + sTemp);
		System.out.println("v1 => " + v1);
		
		// 데이터 삭제하기 1 ==> remove(index)
		//			  ==> 'index'번째 데이터를 삭제
		//            ==> 반환값은 삭제된 데이터가 반환됨
		v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		
		sTemp = (String)v1.remove(0);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println("삭제된 데이터: " + sTemp);
		
		// 데이터 삭제하기 2 ==> remove(삭제할 데이터)
		//			  ==> '삭제할 데이터'를 찾아서 삭제
		//			  ==> '삭제할 데이터'가 여러개면 이 중에 제일 첫번째 자료가 삭제됨
		//			  ==> 반환값: 삭제 성공(true), 삭제 실패(false)
		//			  ==> '삭제할 데이터'가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환해서 사용해야 함
		v1.remove("BBBB");
		System.out.println("'BBBB' 데이터 삭제 후 v1 => " + v1);
		
		// 오류: 인덱스로 인식
//		v1.remove(200);
//		v1.remove(new Integer(200));		// 방법 1 => 자바버전 9 이상에서 사용 불가
		v1.remove(Integer.valueOf(200));	// 방법 2
		System.out.println("'200' 데이터 삭제 후 v1 => " + v1);
		
//		v1.remove('a');
		v1.remove(new Character('a'));
		v1.remove(Character.valueOf('a'));
		System.out.println("'a' 데이터 삭제 후 v1 => " + v1);
	
		v1.remove(true);
		v1.remove(3.14);
		System.out.println("데이터 삭제 후 v1 => " + v1);
		
		// 전체 데이터 삭제하기 ==> clear()
		System.out.println("clear() 삭제 후 v1 => " + v1);
		System.out.println("clear() 후 현재 크기: " + v1.size());
		
		/*
		 제네릭 타입(Generic Type)
		 ==> 클래스 내부에서 사용할 데이터의 타입을 객체를 생성할 때 외부에서 지정해주는 기법으로
		 	객체를 선언할 때 <> 괄호 안에 그 객체의 내부에서 사용할 데이터의 타입을 지정해 주는 것
		 	
		 - 제네릭 타입을 지정해서 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없음
		 - 제네릭 타입으로의 지정은 클래스형으로 함
		  (예: int는 Integer, boolean은 Boolean, char는 Character 등으로 대체해서 사용)
		 - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없음
		*/
		
		Vector<Integer> v2 = new Vector<Integer>();
		Vector<String> v3 = new Vector<>();
		
//		v3.add(100);	// 오류: 다른 종류의 데이터를 저장할 수 없음
		v3.add("안녕하세요");
		
		String sTemp2 = v3.get(0);	// 형 변환 없이 사용할 수 있음
		
		Vector<Vector> vv = new Vector<Vector>();
		Vector<Vector<Vector>> vvv = new Vector<Vector<Vector>>();
		System.out.println();
		System.out.println("-------------------------------------------------");
		
		v3.clear();
		
		System.out.println("v3의 size: " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		Vector<String> v4 = new Vector<String>();
		v4.add("BBB");
		v4.add("DDD");
		
		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		// 데이터 삭제하기 3 ==> removeAll(Collection 객체)
		//		==> 저장된 데이터들 중에서 'collection객체'가 가지고 있는 모든 데이터를 삭제
		//		==> 반환값: 삭제 성공(true), 삭제 실패(false)
		v3.removeAll(v4);
		System.out.println("삭제 후 v3 => " + v3);
		System.out.println("-------------------------------------------------");
		System.out.println();
		
		// 벡터의 데이터들을 순서대로 모두 가져와 사용하고 싶음 => 반복문 사용(주로 for문 사용)
		v3.clear();
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		for(int i = 0; i < v3.size(); i++) {
			System.out.println(i + "번째 자료: " + v3.get(i));
		}
		
		System.out.println("-------------------------------------------------");
		
		// 향상된 for문
		for(String str : v3) {
			System.out.println(str);
		}
	}
}