package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		// ArrayList의 기본적인 사용법은 Vector와 같음
		ArrayList list1 = new ArrayList();

		// add() 메서드를 이용하여 데이터를 추가
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(true);
		list1.add(123.45);

		System.out.println("size ==> " + list1.size());
		System.out.println("list1 => " + list1.toString());
		System.out.println();

		// get() 메서드를 이용하여 데이터를 꺼내옴
		System.out.println("1번째 자료: " + list1.get(1));

		// 데이터 끼워넣기도 동일
		list1.add(3, "zzz");
		System.out.println("list1 => " + list1);

		// 데이터 변경하기
		String sTemp = (String) list1.set(3, "yyy");
		System.out.println("list1 => " + list1);
		System.out.println("sTemp => " + sTemp);
		System.out.println();

		// 삭제도 동일
		list1.remove(3);
		System.out.println("3번째 자료 삭제 후 list1 => " + list1);

		list1.remove("bbb");
		System.out.println("'bbb' 자료 삭제 후 list1 => " + list1);

		System.out.println("----------------------------------------------");

		// 제네릭을 사용할 수 있음
		ArrayList<String> list2 = new ArrayList<String>();

		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");

		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " ==> " + list2.get(i));
		}

		for (String s : list2) {
			System.out.println(s);
		}

		System.out.println("----------------------------------------------");

		// contains(비교 객체) ==> 저장된 데이터 중에 '비교 객체'가 있으면 true, 없으면 false를 반환
		System.out.println("DDDD값 존재 여부: " + list2.contains("DDDD"));
		System.out.println("ZZZZ값 존재 여부: " + list2.contains("ZZZZ"));

		// indexOf(비교 객체)
		// lastIndexOf(비교 객체)
		// ==> 리스트에 '비교 객체'가 있으면 '비교 객체'가 저장된 index 값을 반환하고 없으면 -1을 반환
		// ==> indexOf()는 앞에서부터 뒤쪽으로 검색하고, lastIndexOf()는 뒤에서 앞쪽으로 검색
		// ==> 비교 객체와 같은 데이터가 많으면 첫번째로 찾아진 데이터의 위치값을 반환
		list2.clear();

		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");

		System.out.println("list2 => " + list2);

		System.out.println("DDDD의 위치값: " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ의 위치값: " + list2.indexOf("ZZZZ"));
		System.out.println("DDDD의 위치값: " + list2.lastIndexOf("DDDD"));
		System.out.println("----------------------------------------------");

		// 리스트의 데이터를 배열로 변환하기
		// 1) toArray() ==> Object형 배열로 변환함
		// 2) toArray(new 제네릭타입명[0]) ==> 제네릭 타입의 배열로 변환

		Object[] strArr = list2.toArray();
//		String[] strArr = (String[])list2.toArray(); // 오류: 이 방법은 사용 불가

		System.out.println("List의 개수: " + list2.size());
		System.out.println("배열의 개수: " + strArr.length);

		for (int i = 0; i < strArr.length; i++) {
			System.out.println(i + "번째 자료: " + strArr[i]);
		}
		System.out.println("----------------------------------------------");

		// 제네릭 타입의 배열로 변환해서 가져오기
		String[] strArr2 = list2.toArray(new String[0]);

		for (String s : strArr2) {
			System.out.println(s);
		}
	}
}
