//package kr.or.ddit.basic.homework;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
///*
//	10마리의 말들이 경주하는 프로그램을 작성하시오.
//	
//	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
//	이 클래스는 말 이름(String), 등수(int), 현재 위치(int)를 멤버 변수로 갖는다.
// 	
// 	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.(Comparable인터페이스 구현하기)
// 	
// 	경기 구간은 1~50 구간으로 한다.
// 	
// 	경기 중 중간 중간에 각 말들의 위치를 아래와 같이 나타내시오.
// 	
// 	예)
// 	01번 말: -------->-----------------------------------------
// 	02번 말: ------------------>-------------------------------
// 	...
// 	10번 말: -->-----------------------------------------------
// 	(-는 50개)
//*/
//public class ThreadTest13 {	
//	public static void main(String[] args) {
//		Horse[] hArr = new Horse[10];
//		for (int i = 0; i < 10; i++) {
//			hArr[i] = new Horse((i + 1) + "번 말");
//		}
//
//		for (Horse h : hArr) {
//			h.start();
//		}
//
//		for (Horse h : hArr) {
//			try {
//				h.join();
//			} catch (InterruptedException e) {
//			}
//		}
//
//		ArrayList<Horse> hList = new ArrayList<Horse>();
//		for (int i = 0; i < hArr.length; i++) {
//			hList.add(hArr[i]);
//		}
//		
//		Collections.sort(hList);
//
//		System.out.println("경주 종료");
//		System.out.println("=========================");
//		System.out.println("\t경기 결과");
//		System.out.println("=========================");
//
//		for (int i = 0; i < hList.size(); i++) {
//			System.out.println((i + 1) + "등: " + hList.get(i).getHorseName());
//		}
//	}
//}
//
//// 경기 구간은 1~50 구간
//class Horse extends Thread implements Comparable<Horse> {
//	private String horseName;
//	private int iRank = 0;
//	public static int sRank = 0;
//	private int location = 0;
//
//	// 생성자
//	public Horse(String horseName) {
//		this.horseName = horseName;
//	}
//
//	public String getHorseName() {
//		return horseName;
//	}
//
//	public int getiRank() {
//		return iRank;
//	}
//
//	public void setiRank(int iRank) {
//		this.iRank = iRank;
//	}
//
//	public static int getsRank() {
//		return sRank;
//	}
//
//	public static void setsRank(int sRank) {
//		Horse.sRank = sRank;
//	}
//
//	@Override
//	public void run() {
//		for (int i = 1; i <= 50; i++) {
//			String run = "";
//			location++;
//			for (int j = 1; j <= 50; j++) {
//				if (j == location) {
//					run += ">";
//				} else {
//					run += "-";
//				}
//			}
//			try {
//				Thread.sleep((int) (Math.random() * 500));
//			} catch (InterruptedException e) {
//			}
//			System.out.println(horseName + ": " + run);
//		}
//		
//		sRank++;
//		setiRank(sRank);
//	}
//
//	@Override
//	public int compareTo(Horse hor) {
//		return Integer.compare(iRank, hor.getiRank());
//	}
//}

package kr.or.ddit.basic.homework;

import java.util.Arrays;

/*
	10마리의 말들이 경주하는 프로그램을 작성하시오.
	
	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 
	이 클래스는 말 이름(String), 등수(int), 현재 위치(int)를 멤버 변수로 갖는다.
 	
 	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.(Comparable인터페이스 구현하기)
 	
 	경기 구간은 1~50 구간으로 한다.
 	
 	경기 중 중간 중간에 각 말들의 위치를 아래와 같이 나타내시오.
 	
 	예)
 	01번 말: -------->-----------------------------------------
 	02번 말: ------------------>-------------------------------
 	...
 	10번 말: -->-----------------------------------------------
 	(-는 50개)
*/
public class ThreadTest13 {
	public static void main(String[] args) {
		Horse[] horseArr = new Horse[] { 
				new Horse("01번 말"), new Horse("02번 말"), new Horse("03번 말"), 
				new Horse("04번 말"),	new Horse("05번 말"), new Horse("06번 말"), 
				new Horse("07번 말"), new Horse("08번 말"), new Horse("09번 말"),
				new Horse("10번 말") };

		GameState gs = new GameState(horseArr);

		for (Horse h : horseArr) {
			h.start();
		}
		
		gs.start();
		
		// 말들의 경주가 끝날 때까지 기다린다.
		for (Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println();
		System.out.println("경기 종료");
		System.out.println();
		
		System.out.println("====== 경기 결과 ======");
		
		// 등수의 오름차순 정렬
		Arrays.sort(horseArr);
		
		for (Horse h : horseArr) {
			System.out.println(h);
		}
	}
}

// Horse라는 이름의 쓰레드 클래스
// 이 클래스는 말 이름(String), 등수(int), 현재 위치(int)를 멤버 변수로 갖는다.
class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank = 0; // 경주가 끝난 말의 등수를 구하기위한 변수

	private String horseName;
	private int rank;
	private int location;

	// 생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등입니다.";
	}

	// 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.rank);
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			this.location = i; // 말의 현재 위치 저장

			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
			}
		}

		// 현재 말의 경주가 끝나면 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
	}
}

/*
 * 예) 01번 말: -------->----------------------------------------- 02번 말:
 * ------------------>------------------------------- ... 10번 말:
 * -->-----------------------------------------------
 */
//경기 중 중간 중간에 각 말들의 위치를 나타내는 쓰레드
class GameState extends Thread {
	private Horse[] horseArr; // 경주에 참가하는 말들이 저장된 배열을 저장할 배열 변수

	// 생성자
	public GameState(Horse[] horseArr) {
		this.horseArr = horseArr;
	}

	@Override
	public void run() {
		while (true) {
			// 모든 말의 경주가 끝났는지 여부 검사
			if (Horse.currentRank == horseArr.length) {
				break;
			}
			
			// 제자리에서 출력되도록 보이게하는 빈줄 삽입
			for (int i = 1; i <= 20; i++) {
				System.out.println();
			}
			
			for (Horse h : horseArr) {
				System.out.print(h.getHorseName() + " : ");
				
				for(int i = 1; i <= 50; i++) {				
					// 말의 현재 위치를 비교하여 ">"문자를 출력
					if (h.getLocation() == i) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println(); // 줄바꿈
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}