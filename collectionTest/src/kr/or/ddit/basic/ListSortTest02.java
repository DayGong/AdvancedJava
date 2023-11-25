package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();

		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "강감찬", "010-4444-4444"));
		memList.add(new Member(6, "일지매", "010-5555-5555"));
		memList.add(new Member(2, "변학도", "010-6666-6666"));

		System.out.println("정렬 전");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("================================================");

		Collections.sort(memList);
		
		System.out.println("정렬 전");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("================================================");
		
		// Member의 name값으로 오름차순 정렬하는 외부 정렬 기준 클래스를 이용하여 정렬
		// 클래스명: SortNameAsc
		Collections.sort(memList, new SortNameAsc());

		System.out.println("오름차순 외부 정렬 후");
		for (Member mem : memList) {
			System.out.println(mem);
		}
	}
}

// 회원 정보를 저장하는 클래스
// 회원번호의 오름차순으로 정렬하는 내부 정렬 기준을 지정하여 작성
class Member implements Comparable<Member> {
	private int num; // 회원번호
	private String name; // 회원이름
	private String tel; // 전화번호

	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 회원번호의 오름차순
	@Override
	public int compareTo(Member mem) {
		if (this.num > mem.getNum()) {
			return 1;	// 양수를 반환하면 두 값의 위치가 바뀜
		} else if (this.num < mem.getNum()) {
			return -1;
		} else {
			return 0;
		}
	}
}

// 회원 이름 순으로 오름차순 정렬하는 외부 정렬 클래스
class SortNameAsc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		// compareTo는 내부 정렬(오름차순) 기준
		return mem1.getName().compareTo(mem2.getName());
//		if (mem1.getName().compareTo(mem2.getName()) > 0) {
//			return 1;
//		} else if (mem1.getName().compareTo(mem2.getName()) < 0) {
//			return -1;
//		} else {
//			return 0;
//		}
	}
	
}

