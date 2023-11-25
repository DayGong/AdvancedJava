package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetText {
	public static void main(String[] args) {
		HashSet hs1 = new HashSet();
		
		// Set에 데이터 추가 ==> add()메서드 이용
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set의 개수: " + hs1.size());
		System.out.println("Set 데이터 >> " + hs1);
		System.out.println();
		
		// Set에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때: " + isAdd);
		System.out.println("Set 데이터 >> " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때: " + isAdd);
		System.out.println("Set 데이터 >> " + hs1);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 
		// 해당 자료를 삭제한 후에 추가해주는 방법을 사용한다.
		
		// 삭제하는 메서드	==> remove(삭제할데이터) >> 반환값: 삭제 성공 true, 삭제 실패 false
		// 전체 삭제		==> clear()
		
		// 'FF'데이터를 'EE'로 변경하기
		hs1.remove("FF");
		System.out.println("삭제 후 Set 데이터 >> " + hs1);
		hs1.add("EE");
		System.out.println("Set 데이터 >> " + hs1);
		System.out.println();
		
//		hs1.clear();
//		System.out.println("clear() 후 Set 데이터 >> " + hs1);
		
		
		//Iterator 이용하기
		Iterator it = hs1.iterator(); // Set데이터를 Iterator로 변환하기
		
		// Iterator의 hasNext()메서드
		while(it.hasNext()) {
			// 데이터를 꺼내와서 처리하는 내용을 작성
			
			System.out.println(it.next());
		}
		
		// 향상된 for문 이용하기
		for(Object obj : hs1) {
			System.out.println(obj);
		}
		
		System.out.println("================================================");
		
		// 중복 저장 불가 활용하기
		// 우리반 학생들 중 번호를 이용하여 추첨하는 프로그램
		// 번호는 1부터 30번까지 있고, 추첨할 인원은 3명이다.
		// 당첨 번호를 출력하시오.
		
		// 최소값부터 최대값 사이의 정수형 난수 만들기
		// (int)( Math.random() * (최대값 - 최소값 + 1) + 최소값 )
		
		// 26 ~ 33 사이의 난수 만들기
//		System.out.println( (int)(Math.random() * (33-26+1) + 26) );
		
		HashSet<Integer> testSet = new HashSet<Integer>();
		
		// 추첨할 인원
		while(testSet.size() < 3) {
			testSet.add((int)(Math.random() * 30 + 1));
		}
		
		System.out.println("당첨자 번호 >> " + testSet);
		System.out.println();
		
		// Set형의 자료를 List형으로 변환하기
		ArrayList<Integer> testList = new ArrayList<Integer>(testSet);
		for(int i = 0; i < testList.size(); i++) {
			System.out.println(testList.get(i));
		}
	}
}
