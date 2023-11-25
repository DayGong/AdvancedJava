package kr.or.ddit.basic.homework;

import java.util.ArrayList;
import java.util.Scanner;

/* 
 문제1) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에 이들 중 '김'씨 성의 이름을 모두 출력하시오.
 (데이터 입력은 Scanner 객체를 이용한다.)
*/

public class ArrayListTest02 {
	Scanner sc = new Scanner(System.in);

	ArrayList<String> arName = new ArrayList<>();
	ArrayList<String> arKim = new ArrayList<String>();

	public static void main(String[] args) {
		ArrayListTest02 alT = new ArrayListTest02();
//		alT.method01();
//		alT.method02();
//		alT.method03();
		alT.method04();
	}

	void method01() {
		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 이름 입력: ");
			String name = sc.nextLine();
			arName.add(name);

			// 종료위치는 포함 안 됨
			// 가나다라마바.substring(0,4) ==> 가나다라
			if (arName.get(i).substring(0, 1).contains("김")) {
				arKim.add(arName.get(i));
			}
		}

		System.out.println(arKim);

		sc.close();
	}

	void method02() {
		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 이름 입력: ");
			String name = sc.nextLine();
			arName.add(name);
		}

		for (int i = 0; i < arName.size(); i++) {
			String sName = (String) arName.get(i);

			// .: 임의의 한 문자, *: 앞의 문자가 하나도 없거나 무한히 많을 수 있음
			if (sName.matches("김(.*)")) {
				arKim.add(sName);
			}
		}

		System.out.println("김씨" + arKim);
	}

	void method03() {
		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 이름 입력: ");
			String name = sc.nextLine();
			arName.add(name);
		}

		for (int i = 0; i < 5; i++) {
			if (arName.get(i).indexOf("김") == 0) {
				arKim.add(arName.get(i));
			}
		}

		System.out.println("김씨" + arKim);
	}

	// 선생님 풀이
	void method04() {
		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 이름 입력: ");
			String name = sc.nextLine();
			arName.add(name);
		}

		System.out.println();
		System.out.println("김씨 성을 가진 사람들");

		for (int i = 0; i < arName.size(); i++) {

			if (arName.get(i).charAt(0) == '김') {
				System.out.println(arName.get(i));
			}
		}
	}

	// 선생님 풀이
	void method05() {
		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 이름 입력: ");
			String name = sc.nextLine();
			arName.add(name);
		}

		System.out.println();
		System.out.println("김씨 성을 가진 사람들");

		for (int i = 0; i < arName.size(); i++) {

			if (arName.get(i).startsWith("김")) {
				System.out.println(arName.get(i));
			}
		}
	}
}