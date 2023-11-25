//package kr.or.ddit.basic.homework;
//
//import javax.swing.JOptionPane;
//
///*
//	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성
//	
//	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고,
//	사용자는 showInputDialog()로 입력 받아서 구한다.
//	
//	입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
//	5초 안에 입력이 없으면 게임에 진 것으로 처리한다.
//	
//	5초 안에 입력이 완료되면 승패를 구해서 출력한다.
//	
//	결과 예시)
//	1) 5초 안에 입력을 못했을 때
//	== 결 과 ==
//	시간 초과로 당신이 졌습니다.
//	
//	2) 5초 안에 입력했을 때
//	== 결 과 ==
//	컴퓨터 : 가위
//	사용자 : 바위
//	결과 : 당신이 이겼습니다.
// */
//public class ThreadTest07 {
//	public static void main(String[] args) {
//		Thread thUserIn = new UserInput();
//		Thread thCntDown = new GameCountDown();
//
//		thUserIn.start();
//		thCntDown.start();
//	}
//
//}
//
//// 사용자가 데이터를 입력하는 쓰레드
//class UserInput extends Thread {
//	public static boolean inputCheck = false;
//	String user;
//
//	@Override
//	public void run() {
//		do {
//			user = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하세요.");
//
//			if (!(user.equals("가위") || user.equals("바위") || user.equals("보"))) {
//				System.out.println("가위, 바위, 보만 입력 가능합니다.");
//			}
//		} while (!(user.equals("가위") || user.equals("바위") || user.equals("보")));
//
//		inputCheck = true; // 입력이 완료되면 inputCheck변수값을 true로 변경한다.
//
//		game(user);
//	}
//
//	// 게임 결과를 출력하는 메서드
//	private void game(String user) {
//		String com = comRandom();
//		System.out.println("컴퓨터: " + com);
//		System.out.println("사용자: " + user);
//
//		System.out.println("== 결 과 ==");
//		if (com.equals(user)) {
//			System.out.println("당신은 비겼습니다.");
//		} else if (com.equals("가위")) {
//			victoryCheck(user, "바위");
//		} else if (com.equals("바위")) {
//			victoryCheck(user, "보");
//		} else {
//			victoryCheck(user, "가위");
//		}
//	}
//	
//	// 승리 여부를 확인해 출력하는 메서드
//	private void victoryCheck(String user, String rsp) {
//		if (user.equals(rsp)) {
//			System.out.println("당신은 이겼습니다.");
//		} else {
//			System.out.println("당신은 졌습니다.");
//		}
//	}
//
//	// 컴퓨터의 가위, 바위, 보를 구하는 메서드
//	private String comRandom() {
//		int comRan = (int) (Math.random() * 3) + 1;
//		String com = "";
//
//		switch (comRan) {
//		case 1:
//			com = "가위";
//			break;
//		case 2:
//			com = "바위";
//			break;
//		case 3:
//			com = "보";
//			break;
//		}
//
//		return com;
//	}
//}
//
////카운트다운을 진행하는 쓰레드
//class GameCountDown extends Thread {
//	@Override
//	public void run() {
//		for (int i = 5; i >= 1; i--) {
//			// 입력이 완료되었는지를 검사한다.
//			if (UserInput.inputCheck == true) {
//				// 입력이 완료되면 쓰레드를 종료시킨다.
//				return; // run()메서드가 종료되면 쓰레드도 종료된다.
//			}
//
//			System.out.println(i);
//			try {
//				Thread.sleep(1000); // 1초동안 잠시 멈춘다.
//			} catch (InterruptedException e) {
//			}
//		}
//
//		System.out.println("== 결 과 ==");
//		System.out.println("시간 초과로 당신이 졌습니다.");
//		System.exit(0);
//	}
//}

package kr.or.ddit.basic.homework;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성
	
	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고,
	사용자는 showInputDialog()로 입력 받아서 구한다.
	
	입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초 안에 입력이 없으면 게임에 진 것으로 처리한다.
	
	5초 안에 입력이 완료되면 승패를 구해서 출력한다.
	
	결과 예시)
	1) 5초 안에 입력을 못했을 때
	== 결 과 ==
	시간 초과로 당신이 졌습니다.
	
	2) 5초 안에 입력했을 때
	== 결 과 ==
	컴퓨터 : 가위
	사용자 : 바위
	결과 : 당신이 이겼습니다.
 */
public class ThreadTest07 {
	public static boolean inputCheck; // 입력 완료 여부를 나타내는 변수 선언

	public static void main(String[] args) {
		GameCounter gc = new GameCounter();

		// 난수를 이용하여 컴퓨터의 가위 바위 보 정하기
		String[] data = { "가위", "바위", "보" };
		int index = (int) (Math.random() * 3); // 0 ~ 2 사이의 난수 만들기
		String com = data[index];

		// 사용자의 가위 바위 보 입력 받기
		gc.start();
		String man = null;

		do {
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
			// null Exception을 방지하려면 직접적인 단어와 입력 단어를 비교
//		} while (!("가위".equals(man) || "바위".equals(man) || "보".equals(man)));
		} while (!"가위".equals(man) && !"바위".equals(man) && !"보".equals(man));

		inputCheck = true;

		// 결과 판정하기
		String result = ""; // 결과가 저장될 변수 선언

		/*
		 * if (man.equals(com)) { result = "비겼습니다."; } else if (man.equals("가위") &&
		 * com.equals("보") || man.equals("바위") && com.equals("가위") || man.equals("보") &&
		 * com.equals("바위")) { result = "당신이 이겼습니다."; } else { result = "당신이 졌습니다."; }
		 */
		switch (man + com) {
		case "가위가위":
		case "바위바위":
		case "보보":
			result = "비겼습니다.";
			break;
			
		case "가위보":
		case "바위가위":
		case "보바위":
			result = "당신이 이겼습니다.";
			break;
			
		default:
			result = "당신이 졌습니다.";
			break;
		}

		// 출력하기
		System.out.println();
		System.out.println(result);
		System.out.println("== 결 과 ==");
		System.out.println("컴퓨터: " + com);
		System.out.println("사용자: " + man);
		System.out.println("결과: " + result);
	}
}

class GameCounter extends Thread {
	@Override
	public void run() {
		System.out.println("카운트 다운 시작");
		for (int i = 5; i >= 1; i--) {
			if (ThreadTest07.inputCheck) { // 입력 완료 여부 검사
				return;
			}
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

		System.out.println();
		System.out.println("== 결 과 ==");
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
}