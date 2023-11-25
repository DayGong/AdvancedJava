//package kr.or.ddit.basic;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Scanner;
//
///*
//	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
//	컴퓨터의 숫자는 난수를 이용하여 구한다.(1~9 사이의 숫자)
//	(스트라이크는 S, 볼은 B로 나타낸다.)
//	
//	컴퓨터는 임의의 세 숫자를 만듦
//	사용자는 임의의 세 숫자를 입력
//	
//	컴퓨터 : 2 3 4
//	사용자 : 1 3 2  ==> 1S 1B
//		  4 5 6  ==> 0S 1B
//		  ...
//		  2 3 4  ==> 3S 0B
//	
//	위치가 같고 값이 같으면 스트라이크, 값이 같지만 위치가 다르면 볼
//	
//	예시)
//		컴퓨터의 난수 ==> 9 5 7
//	
//	실행예시)
//		숫자 입력 >> 3 5 6
//		3 5 6 ==> 1S 0B
//		숫자 입력 >> 7 8 9
//		7 8 9 ==> 0S 2B
//		숫자 입력 >> 9 7 5
//		9 7 5 ==> 1S 2B
//		숫자 입력 >> 9 5 7
//		9 5 7 ==> 3S 0B
//		 
//		축하합니다.
//		당신은 4번만에 맞췄습니다.
// */
//
//public class BaseballTest {
//	public static void main(String[] args) {
//		new BaseballTest().start();
//	}
//
//	private void start() {
//		printMain();
//		ArrayList<Integer> comNum = createComRanNum();
//		count(comNum);
//	}
//
//	private void count(ArrayList<Integer> comNum) {
//		int count = 0;
//		int ball = 0;
//		int strike = 0;
//
//		while (true) {
//			ArrayList<Integer> userNum = inputUser();
//
//			for (int i = 0; i < userNum.size(); i++) {
//				if (comNum.contains(userNum.get(i))) {
//					if (comNum.get(i) == userNum.get(i)) {
//						strike++;
//					} else {
//						ball++;
//					}
//				}
//			}
//
//			System.out.println(userNum + " ==> " + strike + "S " + ball + "B");
//
//			if (strike == 3) {
//				System.out.println("축하합니다.");
//				System.out.println("당신은 " + count + "번만에 맞췄습니다.");
//				return;
//			}
//
//			strike = 0;
//			ball = 0;
//			count++;
//		}
//	}
//
//	private ArrayList<Integer> inputUser() {
//		Scanner sc = new Scanner(System.in);
//		ArrayList<Integer> userList = new ArrayList<Integer>();
//
//		System.out.print("숫자 입력    >>  ");
//		String userInput = sc.nextLine();
//
//		String[] userArg = userInput.split(" ");
//
//		for (int i = 0; i < 3; i++) {
//			userList.add(Integer.parseInt(userArg[i]));
//		}
//
//		return userList;
//	}
//
//	private ArrayList<Integer> createComRanNum() {
//		System.out.println("컴퓨터가 1~9 사이의 숫자를 랜덤으로 생성합니다.");
//
//		HashSet<Integer> numSet = new HashSet<Integer>();
//
//		while (numSet.size() < 3) {
//			numSet.add((int) (Math.random() * 9 + 1));
//		}
//
//		ArrayList<Integer> comNumList = new ArrayList<Integer>(numSet);
//		Collections.shuffle(comNumList);
//
//		System.out.print("컴퓨터 번호 >> ");
//		for (int comNum : comNumList) {
//			System.out.printf("%2d ", comNum);
//		}
//		System.out.println();
//
//		return comNumList;
//	}
//
//	private void printMain() {
//		System.out.println("===================================");
//		System.out.println("\t숫    자    야    구    게    임");
//		System.out.println("===================================");
//	}
//}


package kr.or.ddit.basic.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
	컴퓨터의 숫자는 난수를 이용하여 구한다.(1~9 사이의 숫자)
	(스트라이크는 S, 볼은 B로 나타낸다.)
	
	컴퓨터는 임의의 세 숫자를 만듦
	사용자는 임의의 세 숫자를 입력
	
	컴퓨터 : 2 3 4
	사용자 : 1 3 2  ==> 1S 1B
		  4 5 6  ==> 0S 1B
		  ...
		  2 3 4  ==> 3S 0B
	
	위치가 같고 값이 같으면 스트라이크, 값이 같지만 위치가 다르면 볼
	
	예시)
		컴퓨터의 난수 ==> 9 5 7
	
	실행예시)
		숫자 입력 >> 3 5 6
		3 5 6 ==> 1S 0B
		숫자 입력 >> 7 8 9
		7 8 9 ==> 0S 2B
		숫자 입력 >> 9 7 5
		9 7 5 ==> 1S 2B
		숫자 입력 >> 9 5 7
		9 5 7 ==> 3S 0B
		 
		축하합니다.
		당신은 4번만에 맞췄습니다.
 */

public class BaseballTest {
	ArrayList<Integer> numList;
	ArrayList<Integer> userList;
	private Scanner sc = new Scanner(System.in);
	private int strike;
	private int ball;

	public static void main(String[] args) {
		new BaseballTest().start();
	}

	private void start() {
		printMain();
		createNum();
		
		// 확인용으로 난수값을 출력해 본다.
		System.out.println("컴퓨터 난수값 ==> " + numList);
		
		int cnt = 0;		   // 몇 번만에 맞추었는지를 저장하는 변수
		
		do {
			cnt++;
			
			inputNum();		   // 사용자의 입력용 메서드 호출
			
			ballCount();	   // 볼카운트 메서드 호출
			
		} while (strike != 3); // 3 스트라이크가 될 때까지 반복한다.
		
		System.out.println("축하합니다.");
		System.out.println("당신은 " + cnt + "번째만에 맞췄습니다.");
	}

	private void ballCount() {
		strike = 0;
		ball = 0; // 스트라이크 개수와 볼의 개수 초기화

		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if (numList.get(i) == userList.get(j)) { // 값이 같은지 검사
					if (i == j) { // 위치(첨자)가 같은지 검사
						strike++;
					} else {
						ball++;
					}
				}
			}
		}

		// 볼 카운트 결과를 출력한다.
		System.out.println(
				userList.get(0) + " " + userList.get(1) + " " + userList.get(2) + " ==> " + strike + "S " + ball + "B");
	}

	// 사용자로부터 3개의 정수를 입력 받아 List에 저장하는 메서드
	// 입력한 정수들은 서로 중복되지 않아야 한다.
	private void inputNum() {
		int n1, n2, n3; // 입력한 값을 저장할 변수 선언

		do {
			System.out.println();
			System.out.print("숫자 입력    >>  ");
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			n3 = sc.nextInt();

			// 입력값 중복 검사
			if (n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 숫자는 입력 불가입니다.");

			}
		} while (n1 == n2 || n1 == n3 || n2 == n3);

		// 입력한 데이터들을 List에 저장한다.
		userList = new ArrayList<Integer>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}

	private void createNum() {
		System.out.println("컴퓨터가 1~9 사이의 숫자를 랜덤으로 생성합니다.");

		Set<Integer> numSet = new HashSet<Integer>();

		// 난수 3개 만들기
		while (numSet.size() < 3) {
			numSet.add((int) (Math.random() * 9 + 1));
		}

		// 만들어진 난수를 List에 저장하기
		numList = new ArrayList<Integer>(numSet);

		// 데이터 섞기
		Collections.shuffle(numList);
	}

	private void printMain() {
		System.out.println("===================================");
		System.out.println("\t숫    자    야    구    게    임");
		System.out.println("===================================");
	}
}
