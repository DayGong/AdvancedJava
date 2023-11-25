package kr.or.ddit.basic.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 * 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.
 * (이때, 총점은 세 과목의 점수를 이용해서 초기화한다.
 * 
 * 이 Student 객체는 List에 저장하여 관리한다.
 * 
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
 * 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되도록 외부 정렬 기준 클래스를 작성하여
 * 정렬된 결과를 출력하시오
 * (단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.)
 */

public class StudentTest {

	// 등수를 구하는 메서드
	public void createRank(ArrayList<Student> list) {
		for (Student std1 : list) { 	// 기준 데이터(등수 구할 데이터)를 구하기 위한 반복문
			int rank = 1;				// 처음에는 1등으로 설정해놓고 시작한다.
			for (Student std2 : list) {	// 비교 대상을 나타내는 반복문
				
				// 기준 데이터보다 비교 대상 데이터가 더 크면 rank값을 증가시킨다.
				if (std1.getScore() < std2.getScore()) {
					rank++;
				}
			}
			
			std1.setRank(rank); // 구해진 등수를 입력시킨다.
		}
	}

	public static void main(String[] args) {
		StudentTest st = new StudentTest();
		ArrayList<Student> stdList = new ArrayList<Student>();

		stdList.add(new Student(1, "홍길동", 90, 80, 70));
		stdList.add(new Student(5, "이순신", 100, 60, 100));
		stdList.add(new Student(9, "성춘향", 70, 40, 50));
		stdList.add(new Student(3, "강감찬", 100, 60, 100));
		stdList.add(new Student(6, "일지매", 45, 75, 100));
		stdList.add(new Student(2, "변학도", 10, 15, 20));

		// 등수 구하는 메서드 호출
		st.createRank(stdList);

		System.out.println("================================================");

		// 내부 정렬 기준(학번의 오름차순)
		Collections.sort(stdList);

		System.out.println("학번의 오름차순 정렬 후");
		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("================================================");

		Collections.sort(stdList, new ScoreDesc());
		System.out.println("총점 정렬 후");
		for (Student std : stdList) {
			System.out.println(std);
		}
//		System.out.println("================================================");
//
//		System.out.println("등수 추가 입력");
//		int rank = 0;
//		for (int i = 0; i < stdList.size(); i++) {
//			rank++;
//			if (i != 0 && stdList.get(i - 1).getScore() == stdList.get(i).getScore()) {
//				rank--;
//			}
//			stdList.get(i).setRank(rank);
//		}
//
//		System.out.println("등수 추가 후");
//		for (Student std : stdList) {
//			System.out.println(std);
//		}
//		System.out.println("================================================");

	}
}

class Student implements Comparable<Student> {
	private int num; // 학번
	private String name; // 이름
	private int kor; // 국어점수
	private int eng; // 영어점수
	private int math; // 수학점수
	private int score; // 총점
	private int rank; // 등수

	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.score = kor + eng + math;
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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", score=" + score + ", rank=" + rank + "]";
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 학번의 오름차순
	@Override
	public int compareTo(Student std) {
		
		// Wrapper 클래스를 이용하기
		// Wrapper클래스는 이미 내부 정렬 기준이 구현되어 있음
//		return new Integer(this.num).compareTo(std.getNum());	// 방법 1
		return Integer.compare(this.num, std.getNum());			// 방법 2
		
		// 내림차순으로 하고싶은 경우
//		return new Integer(this.num).compareTo(std.getNum()) * -1;
	}
}

// 성적 순으로 내림차순 정렬하는 외부 정렬 클래스
class ScoreDesc implements Comparator<Student> {
	// 성적 순 내림차순
	@Override
	public int compare(Student std1, Student std2) {
		if (std1.getScore() == std2.getScore()) {
			return std1.getName().compareTo(std2.getName());
		} else {
			return Integer.compare(std1.getScore(), std2.getScore()) * -1;
		}
	}
	
//	@Override
//	public int compare(Student std1, Student std2) {
//
//		if (std1.getScore() > std2.getScore()) {
//			return -1;
//		} else if (std1.getScore() < std2.getScore()) {
//			return 1;
//		} else {
//			compareTo(std1, std2);
//			return 0;
//		}
		
//		return Integer.compare(std1.getScore(), std2.getScore()) * -1;
//	}
	

	// 총점이 같으면 이름 순 오름차순으로 정렬
	public int compareTo(Student std1, Student std2) {
//		if (std1.getName().compareTo(std2.getName()) > 0) {
//			return 1;
//		} else if (std1.getName().compareTo(std2.getName()) < 0) {
//			return -1;
//		} else {
//			return 0;
//		}
		return std1.getName().compareTo(std2.getName());
	}

}