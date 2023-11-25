package kr.or.ddit.basic.homework;

import java.util.ArrayList;
import java.util.Scanner;

/* 
문제2) 5명의 별명을 입력 받아 ArrayList에 저장한 후에 별명의 길이가 제일 긴 별명을 출력하시오.
(단, 입력하는 별명의 길이는 모두 다르게 입력한다.) ==> ArrayListTest03 클래스로
*/

public class ArrayListTest03 {
	ArrayList<String> alName = new ArrayList<>();
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayListTest03 alT = new ArrayListTest03();
		
		alT.myAlias();
		alT.teacherAlias();
	}

	void myAlias() {
		String longName = "";

		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 별명: ");
			String name = sc.nextLine();
			alName.add(name);
		}
		sc.close();

		for (int i = 0; i < 4; i++) {
			if (alName.get(i).length() > alName.get(i + 1).length()) {
				longName = alName.get(i);
			} else {
				longName = alName.get(i + 1);
			}
		}

		System.out.println("가장 긴 별명: " + longName);
	}
	
	// 선생님 풀이
	void teacherAlias() {
		for (int i = 0; i < 5; i++) {
			System.out.print((i + 1) + "번째 별명: ");
			String name = sc.nextLine();
			alName.add(name);
		}
		System.out.println();
		
		// 제일 긴 별명이 저장될 변수를 선언하고,
		// 이 변수를 List의 첫번째 변수로 초기화
		String maxAlias = alName.get(0);
		
		for(int i = 1; i < alName.size(); i++) {
			if(maxAlias.length() < alName.get(i).length()) {
				maxAlias = alName.get(i);
			}
		}
		
		System.out.println("제일 긴 별명: " + maxAlias);
	}
}
