package kr.or.ddit.basic.homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class PhoneBookTest {
	Scanner scan = new Scanner(System.in);
	HashMap<String, Phone> phoneMap = new HashMap<String, Phone>();
	String korPattern = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";
	String telPattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";

	public static void main(String[] args) {
		new PhoneBookTest().start();
	}

	private void start() {
		choice();
	}

	private void choice() {
		while (true) {
			printMenu();
			String choice = scan.nextLine();

			switch (choice) {
			case "1":
				signUp();
				break;
			case "2":
				update();
				break;
			case "3":
				delete();
				break;
			case "4":
				search();
				break;
			case "5":
				allPrint();
				break;
			case "0":
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("입력이 잘못되었습니다. 다시 입력해주세요.");
			}
		}
	}

	private void allPrint() {
		int i = 1;

		printTitle("\t전화번호\t\t전체출력");
		printTitle(" 번호\t이름\t전화번호\t\t주소");
 
		Set<String> keySet = phoneMap.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Phone value = phoneMap.get(key);
			System.out.printf(" %d %8s   %13s %10s\n", 
                   i, value.getName(), value.getTel(), value.getAddr());
			i++;
		}
	}

	private void search() {
		printTitle("검색할 전화번호 정보의 이름을 입력하세요.");
		
		String name = input(korPattern, "이름 >> ");

		if (!phoneMap.containsKey(name)) {
			System.out.println("존재하지않는 이름입니다.");
			return;
		}
		
		printTitle(" 번호\t이름\t전화번호\t\t주소");
		
		Phone value = phoneMap.get(name);
		System.out.printf(" %d %8s   %13s %10s", 
                  1, value.getName(), value.getTel(), value.getAddr());
	}

	private void delete() {
		printTitle("삭제할 전화번호 정보의 이름을 입력하세요.");

		String name = input(korPattern, "이름 >> ");

		if (!phoneMap.containsKey(name)) {
			System.out.println("존재하지않는 이름입니다.");
			return;
		}

		phoneMap.remove(name);
		System.out.println("정보가 삭제되었습니다.");
	}

	private void update() {
		Phone phone = new Phone();

		printTitle("수정할 전화번호 정보의 이름을 입력하세요.");
		
		String name = input(korPattern, "이름 >> ");
		
		if (!phoneMap.containsKey(name)) {
			System.out.println("존재하지않는 이름입니다.");
			return;
		}
		
		String tel = input(telPattern, "전화번호 >> ");
		String addr = input(korPattern, "주소 >> ");

		phone.setName(name);
		phone.setTel(tel);
		phone.setAddr(addr);

		phoneMap.put(name, phone);

		System.out.println("정보 수정에 성공하셨습니다.");
	}

	private void signUp() {
		Phone phone = new Phone();

		printTitle("새롭게 등록할 전화번호 정보를 입력하세요.");

		String name = input(korPattern, "이름 >> ");
		
		if (phoneMap != null && phoneMap.containsKey(name)) {
			System.out.println(name + "은 이미 등록된 사람입니다.");
			return;
		}
		
		String tel = input(telPattern, "전화번호 >> ");
		String addr = input(korPattern, "주소 >> ");

		phone.setName(name);
		phone.setTel(tel);
		phone.setAddr(addr);

		phoneMap.put(name, phone);

		System.out.println();
		System.out.println("'" + name + "'의 전화번호 등록 완료!!");
	}
	
	private String input(String pattern, String input) {
		String info;

		do {
			System.out.print(input);
			info = scan.nextLine();
		} while (!Pattern.matches(pattern, info));
		
		return info;
	}

	private void printTitle(String text) {
		System.out.println();
		System.out.println("====================================================");
		System.out.println(text);
		System.out.println("====================================================");
	}

	private void printMenu() {
		printTitle("\t전\t화\t번\t호\t부");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("====================================================");
		System.out.print("작업 번호 >> ");
	}
}

class Phone {
	private String name;
	private String tel;
	private String addr;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}

//package kr.or.ddit.basic;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Scanner;
//
///*
//	문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고,
//	Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
//	
//	이 프로그램에는 아래의 메뉴가 있고 이 메뉴를 구현하시오.
//	
//	==========================================
//	1. 전화번호 등록
//	2. 전화번호 수정
//	3. 전화번호 삭제
//	4. 전화번호 검색
//	5. 전화번호 전체 출력
//	0. 프로그램 종료
//	==========================================
//
//	- 사용되는 Map의 구조는 key값으로 '등록될 사람의 이름'을 사용하고
//	  value값으로는 'Phone클래스의 인스턴스'로 한다.
//	  (HashMap<String, Phone>)
//	  
//	- 삭제, 검색 기능은 '이름'을 받아 처리한다.
//	
//	실행 예시)
//	==========================================
//	1. 전화번호 등록
//	2. 전화번호 수정
//	3. 전화번호 삭제
//	4. 전화번호 검색
//	5. 전화번호 전체 출력
//	0. 프로그램 종료
//	==========================================
//	작업번호 >> 1
//	
//	새롭게 등록할 전화번호 정보를 입력하세요.
//	이     름 >> 홍길동
//	전화번호 >> 010-1111-1111
//	주      소 >> 대전시 중구 오류동
//	
//	'홍길동'의 전화번호 등록 완료!!
//	
//	==========================================
//	1. 전화번호 등록
//	2. 전화번호 수정
//	3. 전화번호 삭제
//	4. 전화번호 검색
//	5. 전화번호 전체 출력
//	0. 프로그램 종료
//	==========================================
//	작업번호 >> 1
//	
//	새롭게 등록할 전화번호 정보를 입력하세요.
//	이     름 >> 홍길동
//	
//	'홍길동'은 이미 등록된 사람입니다.
//	
//	==========================================
//	1. 전화번호 등록
//	2. 전화번호 수정
//	3. 전화번호 삭제
//	4. 전화번호 검색
//	5. 전화번호 전체 출력
//	0. 프로그램 종료
//	==========================================
//	작업번호 >> 5
//	
//	==========================================
//	 번호            이름             전화번호                         주소
//	==========================================
//	 1             홍길동          010-1111-1111  대전시 중구 오류동
//	 ...
//	==========================================
//	출력 완료
//	
//	==========================================
//	1. 전화번호 등록
//	2. 전화번호 수정
//	3. 전화번호 삭제
//	4. 전화번호 검색
//	5. 전화번호 전체 출력
//	0. 프로그램 종료
//	==========================================
//	작업번호 >> 0
//	
//	프로그램을 종료합니다.
// */
//
//public class PhoneBookTest {
//	HashMap<String, Phone> phoneBookMap;
//	Scanner scan;
//	String korPattern = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";
//	String telPattern = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
//
//	// 생성자
//	public PhoneBookTest() {
//		phoneBookMap = new HashMap<String, Phone>();
//		scan = new Scanner(System.in);
//	}
//
//	public static void main(String[] args) {
//		new PhoneBookTest().phoneBookStart();
//	}
//
//	private void phoneBookStart() {
//		System.out.println("**********************************");
//		System.out.println("전 화 번 호 관 리 프 로 그 램");
//		System.out.println("**********************************");
//		System.out.println();
//
//		while (true) {
//			int choice = displayMenu();
//
//			switch (choice) {
//			case 1: // 등록
//				insert();
//				break;
//			case 2: // 수정
//				update();
//				break;
//			case 3: // 삭제
//				delete();
//				break;
//			case 4: // 검색
//				search();
//				break;
//			case 5: // 전체 출력
//				displayAll();
//				break;
//			case 0: // 종료
//				System.out.println("프로그램을 종료합니다.");
//				return;
//			default:
//				System.out.println("입력이 잘못되었습니다. 다시 입력해주세요.");
//			}
//		}
//	}
//
//	// 전체 자료를 출력하는 메서드
//	private void displayAll() {
//
//		printTitle("\t전화번호\t\t전체출력");
//		printTitle(" 번호\t이름\t전화번호\t\t주소");
//
//		if (phoneBookMap.size() == 0) {
//			System.out.println("\t등록된 전화번호 정보가 없습니다.");
//		} else {
//			int num = 0; // 번호 출력용
//
//			Iterator<String> keyIt = phoneBookMap.keySet().iterator();
//			while (keyIt.hasNext()) {
//				num++;
//				String key = keyIt.next(); // key값(등록된 이름) 구하기
//				Phone p = phoneBookMap.get(key); // value값(Phone 객체) 구하기
//				System.out.println(num + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
//			}
//		}
//		System.out.println("====================================================");
//		System.out.println("출력 완료");
//	}
//
//	// 전화번호 정보를 검색하는 메서드
//	private void search() {
//		printTitle("검색할 전화번호 정보의 이름을 입력하세요.");
//
//		System.out.print("이름 >> ");
//		String name = scan.next();
//
//		// 검색할 사람이 있는지 검사
//		if (!phoneBookMap.containsKey(name)) {
//			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
//			return;
//		}
//
//		Phone p = phoneBookMap.get(name);
//		System.out.println(name + "씨의 전화번호 정보");
//		System.out.println("----------------------------------------------------");
//		System.out.println(" 이 름 : " + p.getName());
//		System.out.println(" 전 화 : " + p.getTel());
//		System.out.println(" 주 소 : " + p.getAddr());
//		System.out.println("----------------------------------------------------");
//	}
//
//	// 전화번호 정보를 삭제하는 메서드
//	private void delete() {
//		printTitle("삭제할 전화번호 정보의 이름을 입력하세요.");
//
//		System.out.print("이름 >> ");
//		String name = scan.next();
//
//		// 삭제할 사람이 있는지 검사
//		if (!phoneBookMap.containsKey(name)) {
//			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
//			return;
//		}
//
//		phoneBookMap.remove(name);
//		System.out.println("'" + name + "'씨의 전화번호 정보가 삭제되었습니다.");
//	}
//
//	// 전화번호 정보를 수정하는 메서드
//	private void update() {
//		printTitle("수정할 전화번호 정보의 이름을 입력하세요.");
//
//		System.out.print("이름 >> ");
//		String name = scan.next();
//
//		// 수정할 사람이 이미 등록되어 있는지 검사
//		if (!phoneBookMap.containsKey(name)) {
//			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
//			return;
//		}
//
//		System.out.print("새로운 전화번호 >> ");
//		String newTel = scan.next();
//
//		System.out.print("새로운 주소 >> ");
//		String newAddr = scan.next();
//
//		// 수정 작업 ==> 같은 key값에 새로운 value값을 추가하면 된다.
//		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
//
//		System.out.println("'" + name + "'의 전화번호 정보가 수정되었습니다.");
//	}
//
//	// 새로운 전화번호 정보를 등록하는 메서드
//	// 이미 등록된 사람은 등록되지 않음(동명이인 없음)
//	private void insert() {
//		printTitle("새롭게 등록할 전화번호 정보를 입력하세요.");
//
//		System.out.print("이름 >> ");
//		String name = scan.next();
//
//		// 이미 등록된 사람인지 검사한다.
//		if (phoneBookMap.containsKey(name)) {
//			System.out.println(name + "은 이미 등록된 사람입니다.");
//			return;
//		}
//
//		System.out.print("전화번호 >> ");
//		String tel = scan.next();
//		
//		scan.nextLine();	// 입력 버퍼 비우기
//		// nextLine()명령어 실행 전 next(), nextInt(), nextFloat()등이 있는 경우 찌꺼기가 남기 때문에 사용
//
//		System.out.print("주소 >> ");
//		String addr = scan.nextLine();
//
////		Phone p = new Phone(name, tel, addr);
////		phoneBookMap.put(name, p);
//
//		phoneBookMap.put(name, new Phone(name, tel, addr));
//
//		System.out.println("'" + name + "'의 전화번호 등록 완료!!");
//	}
//
//	private void printTitle(String text) {
//		System.out.println();
//		System.out.println("====================================================");
//		System.out.println(text);
//		System.out.println("====================================================");
//	}
//
//	// 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드
//	private int displayMenu() {
//		printTitle("\t전\t화\t번\t호\t부");
//		System.out.println("1. 전화번호 등록");
//		System.out.println("2. 전화번호 수정");
//		System.out.println("3. 전화번호 삭제");
//		System.out.println("4. 전화번호 검색");
//		System.out.println("5. 전화번호 전체 출력");
//		System.out.println("0. 프로그램 종료");
//		System.out.println("====================================================");
//		System.out.print("작업 번호 >> ");
//
//		return scan.nextInt();
//	}
//}
//
//class Phone {
//	private String name;
//	private String tel;
//	private String addr;
//
//	public Phone() {
//	}
//
//	public Phone(String name, String tel, String addr) {
//		this.name = name;
//		this.tel = tel;
//		this.addr = addr;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getTel() {
//		return tel;
//	}
//
//	public void setTel(String tel) {
//		this.tel = tel;
//	}
//
//	public String getAddr() {
//		return addr;
//	}
//
//	public void setAddr(String addr) {
//		this.addr = addr;
//	}
//}