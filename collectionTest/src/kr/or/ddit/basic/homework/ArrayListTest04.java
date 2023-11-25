package kr.or.ddit.basic.homework;

import java.util.ArrayList;
import java.util.Scanner;

/* 
문제3) 5명의 별명을 입력 받아 ArrayList에 저장한 후에 별명의 길이가 제일 긴 별명을 출력하시오.
(단, 입력하는 별명의 길이가 같은 것이 있을 수 있다.) ==> ArrayListTest04 클래스로
*/

public class ArrayListTest04 {
	ArrayList<String> alName = new ArrayList<>();
	ArrayList<String> alLong = new ArrayList<String>();
	Scanner sc = new Scanner(System.in);
	int iMax = 0;

	public static void main(String[] args) {
		ArrayListTest04 alT = new ArrayListTest04();

		alT.myAlias();
		alT.teacherAlias();
	}

	void myAlias() {

		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 별명: ");
			String name = sc.nextLine();
			alName.add(name);
		}
		sc.close();

		for (int i = 0; i < 4; i++) {
			if (alName.get(i).length() > alName.get(i + 1).length()) {
				iMax = alName.get(i).length();
			} else {
				iMax = alName.get(i + 1).length();
			}
		}

		for (int i = 0; i < alName.size(); i++) {
			if (alName.get(i).length() == iMax) {
				alLong.add(alName.get(i));
			}
		}

		System.out.println("가장 긴 별명: " + alLong);
	}

	// 선생님 풀이
	void teacherAlias() {
		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 별명: ");
			String name = sc.nextLine();
			alName.add(name);
		}
		System.out.println();

		// 제일 긴 별명의 길이가 저장될 변수를 선언하고,
		// 이 변수에는 List의 첫번째 데이터의 길이로 초기화한다.
		iMax = alName.get(0).length();

		for (int i = 1; i < alName.size(); i++) {
			if (iMax < alName.get(i).length()) {
				iMax = alName.get(i).length();
			}
		}

		System.out.println("제일 긴 별명들");
		for (String alias : alName) {
			if (iMax == alias.length()) {
				System.out.println(alias);
			}
		}
	}
}
