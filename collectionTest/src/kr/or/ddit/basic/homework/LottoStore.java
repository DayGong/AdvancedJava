package kr.or.ddit.basic.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
	로또 판매를 관리하는 프로그램을 작성하시오.
	
	실행 예시)
	==================================
	Lotto 판매 프로그램
	==================================
	1. Lotto 판매
	2. 프로그램 종료
	----------------------------------
	메뉴 선택 >> 1
	
	Lotto 판매 시작
	(1000원에 로또 번호 하나입니다.)
	(로또 번호는 최대 100개까지 가능합니다.)
	
	금액 입력 >> 900
	입력 금액이 너무 적습니다. 로또 번호 판매 실패!!
	
	==================================
	Lotto 판매 프로그램
	==================================
	1. Lotto 판매
	2. 프로그램 종료
	----------------------------------
	메뉴 선택 >> 1
 	
 	Lotto 판매 시작
	(1000원에 로또 번호 하나입니다.)
	(로또 번호는 최대 100개까지 가능합니다.)
	
	금액 입력 >> 101000
	입력 금액이 너무 많습니다. 로또 번호 판매 실패!!
	
	==================================
	Lotto 판매 프로그램
	==================================
	1. Lotto 판매
	2. 프로그램 종료
	----------------------------------
	메뉴 선택 >> 1
 	
 	Lotto 판매 시작
	(1000원에 로또 번호 하나입니다.)
	(로또 번호는 최대 100개까지 가능합니다.)
	
	금액 입력 >> 2500
	
	행운의 로또 번호는 아래와 같습니다.
	로또 번호 1 >>  3  2  5  6  7  8
	로또 번호 2 >>  7  8 10 22 31 42 (정렬이 되어 나옴)
	
	받은 금액은 2500원이고, 거스름돈은 500원입니다.
	
	==================================
	Lotto 판매 프로그램
	==================================
	1. Lotto 판매
	2. 프로그램 종료
	----------------------------------
	메뉴 선택 >> 2
	
	감사합니다.
	안녕히 가세요.
 */
public class LottoStore {
	private Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		new LottoStore().start();
	}

	private void start() {
		printStart();
		startChoice();
	}

	private void printStart() {
		System.out.println();
		System.out.println("==================================");
		System.out.println("Lotto 판매 프로그램");
		System.out.println("==================================");
		System.out.println("1. Lotto 판매");
		System.out.println("2. 프로그램 종료");
		System.out.println("----------------------------------");
	}

	private void startChoice() {
		while (true) {
			System.out.print("메뉴 선택 >> ");
			switch (sc.nextLine()) {
			case "1":
				sellLotto();
				break;
			case "2":
				bye();
				break;
//				return;
			default:
				System.out.println("다시 입력해주세요.");
			}
		}
	}

	private void bye() {
		System.out.println();
		System.out.println("감사합니다.");
		System.out.println("안녕히 가세요");
		System.exit(0);
	}

	private void sellLotto() {
		printAmount();
		checkAmount();
	}

	private void printAmount() {
		System.out.println();
		System.out.println("Lotto 판매 시작");
		System.out.println("(1000원에 로또 번호 하나입니다.)");
		System.out.println("(로또 번호는 최대 100개까지 가능합니다.)");
		System.out.print("금액 입력 >> ");
	}

	private void checkAmount() {
		int amount = Integer.parseInt(sc.nextLine());
		System.out.println();

		if (amount < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또 번호 판매 실패!!");
		} else if (amount > 100000) {
			System.out.println("입력 금액이 너무 많습니다. 로또 번호 판매 실패!!");
		} else {
			sellLotto(amount, amount % 1000);
		}

		start();
	}

	private void sellLotto(int amount, int balance) {
		int lottoCount = amount / 1000;

		for (int i = 0; i < lottoCount; i++) {
			HashSet<Integer> lottoSet = new HashSet<Integer>();

			while (lottoSet.size() < 6) {
				lottoSet.add((int) (Math.random() * 45 + 1));
			}
			
			ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
			Collections.sort(lottoList);

			System.out.print("로또 번호 " + (i + 1) + ">> ");
			for (int lottoNum : lottoList) {
				System.out.printf("%2d ", lottoNum);
			}
			System.out.println();
			
			lottoSet.clear(); // 작업할 때 만들어진 데이터 모두 삭제
		}

		printbalance(amount, balance);
	}

	private void printbalance(int amount, int balance) {
		System.out.println();
		System.out.println("받은 금액은 " + amount + "원이고, " 
					+ "거스름돈은 " + balance + "원입니다.");
	}
}
