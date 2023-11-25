package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest01 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();

		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("강감찬");
		list.add("이순신");

		System.out.println("정렬 전 >> " + list);

		Collections.sort(list);
		System.out.println("정렬 후 >> " + list);

		Collections.shuffle(list); // 자료 섞기
		System.out.println("자료 섞기 후 >> " + list);
		
		// 외부 정렬 기준 클래스를 지정해서 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 >> " + list);

	}
}

// 외부 정렬 기준을 정해주는 class를 작성
// Comparator인터페이스를 구현해서 작성해야함
class Desc implements Comparator<String> {

	// compare() 메서드를 이용해서 정렬하고자하는 기준을 정해줌
	// compare() 메서드의 반환값의 의미
	@Override
	public int compare(String str1, String str2) {
		if (str1.compareTo(str2) > 0) {
			return -1;
		} else if (str1.compareTo(str2) < 0) {
			return 1;
		} else {
			return 0;
		}
	}

}