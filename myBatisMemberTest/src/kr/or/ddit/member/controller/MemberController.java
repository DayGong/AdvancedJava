package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberController {
	private Scanner scan;
	private IMemberService service; // Service객체가 저장될 변수 선언
	
	// 생성자
	public MemberController() {
		scan = new Scanner(System.in);
		service = MemberServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new MemberController().MemStart();
	}
	
	private void MemStart() {
		while (true) {
			String choice = printMenu().trim();

			switch (choice) {
			case "1":
				insertMember();
				break;
			case "2":
				deleteMember();
				break;
			case "3":
				updateMember();
				break;
			case "4":
				selectAll();
				break;
			case "5":
				updateMember2();
				break;
			case "0":
				printTitle("작업을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	private void selectAll() {
		printTitle("전체 자료 출력");
		
		printTitle("MEM_ID\tMEM_PASS\tMEM_NAME\tMEM_TEL\t\tMEM_ADDR");
		
		// 전체 회원 정보 가져오기
		List<MemberVO> memList = service.getAllMember();
		
		if(memList == null || memList.size() == 0) {
			System.out.println("등록된 회원 정보가 없습니다.");
		} else {
			for (MemberVO memVo : memList) {
				System.out.println(memVo);
			}
		}
	}
	
	// update2 메서드
		private void updateMember2() {
			System.out.println();
			System.out.println(" 수정할 회원 정보를 입력하세요.");
			System.out.print(" 수정할 회원 id 입력 >> ");
			String memId=scan.next();

			int count = service.getMemberCount(memId);
			if(count==0) {
				System.out.println(memId+" 는(은) 없는 id입니다.");
				System.out.println("수정 작업을 종료합니다.");
				return ;
			}
			
			int num;
			String updateField = null;
			String updateTitle =null;
			
			do {
				System.out.println(" 1 비밀번호 2 회원이름 3 전화번호 4 회원주소");
				System.out.print(" 수정할 항목 선택 >>");
				num= scan.nextInt();
				if( num <1 || num>4) {
					System.out.println("다시 선택");
				}
			}while (num<1 || num >4);
			
			switch (num) {
			case 1: updateField = "mem_pass" ; updateTitle ="비밀번호 "; break;
			case 2: updateField = "mem_name" ; updateTitle ="이름 "; break;
			case 3: updateField = "mem_tel" ; updateTitle ="전화번호 "; break;
			case 4: updateField = "mem_addr" ; updateTitle ="주소 "; break;
			}
			    
			scan.nextLine();
			System.out.println();
			System.out.print("새로운 "+ updateTitle + "입력 >>");
			String updateData =scan.nextLine();
			
			// key값 정보 ==> 회원ID(MEMID), 수정할 컬럼명(FIELD), 수정할 데이터(DATA)
			Map<String, String> paramMap = new HashMap<String, String>();
			
			paramMap.put("MEMID", memId);
			paramMap.put("FIELD", updateField);
			paramMap.put("DATA", updateData);
			
			int cnt = service.updateMember2(paramMap);
			
			if (cnt == 1) {
				printTitle("자료 수정 성공");
			} else {
				printTitle("자료 수정 실패");
			}
		}
	
	int nCount = 0; // 수정을 하지않는 횟수를 저장하는 변수
	
	// update 메서드
	private void updateMember() {
		printTitle("자료 수정");
		
		String memId;
		int count;
		
		do {
			memId = input("수정할 MEM_ID >> ");
			count = service.getMemberCount(memId);

			if (count < 1) {
				printTitle("없는 ID입니다.",
						"다시 입력해주세요.");
			}

		} while (count < 1);
		
		MemberVO memVo = service.selectMember(memId);
		
		memVo.setMem_pass(changeYesNo("MEM_PASS", memVo.getMem_pass()));
		memVo.setMem_name(changeYesNo("MEM_NAME", memVo.getMem_name()));
		memVo.setMem_tel(changeYesNo("MEM_TEL", memVo.getMem_tel()));
		memVo.setMem_addr(changeYesNo("MEM_ADDR", memVo.getMem_addr()));
		
		if (nCount == 4) {
			printTitle("수정을 취소합니다.");
			return;
		}
		nCount = 0;
		
		int res = service.updateMember(memVo);
		
		if (res == 1) {
			printTitle("자료 수정 성공");
		} else {
			printTitle("자료 수정 실패");
		}
	}
	
	// 수정 여부를 확인하는 메서드
	private String changeYesNo(String text, String memValue) {
		while(true) {
			String memColumn = "";
			
			System.out.print(text + "를 변경하시겠습니까?(y/n) >> ");
			String yesNo = scan.nextLine().toLowerCase();
		
			switch(yesNo) {
			case "y":
				memColumn = input("변경할 " + text + " >> "); 
				break;
			case "n":
				nCount++;
				memColumn = memValue;
				break;
			default:
				printTitle("다시 입력해주세요.");
				continue;
			}
			
			return memColumn;
		}
	}

	// delete메서드
	private void deleteMember() {
		printTitle("자료 삭제");
		
		String memId;
		int count;
		
		do {
			memId = input("삭제할 MEM_ID >> ");
			count = service.getMemberCount(memId);

			if (count < 1) {
				printTitle("없는 ID입니다.",
						"다시 입력해주세요.");
			}
			
		} while (count < 1);
		
		int res = service.deleteMember(memId);
		
		if (res == 1) {
			printTitle("자료 삭제 성공");
		} else {
			printTitle("자료 삭제 실패");
		}
		
	}

	// insert메서드
	private void insertMember() {
		
		printTitle("자료 추가");

		String memId;
		int count;
		
		do {
			memId = input("MEM_ID >> ");
			count = service.getMemberCount(memId);

			if (count > 0) {
				printTitle("중복된 ID입니다.", "다시 입력해주세요.");
				System.out.println();
			}
			
		} while (count > 0);

		String mem_pass = input("MEM_PASS >> ");
		String mem_name = input("MEM_NAME >> ");
		String mem_tel = input("MEM_TEL >> ");
		String mem_addr = input("MEM_ADDR >> ");
			
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(mem_pass);
		memVo.setMem_name(mem_name);
		memVo.setMem_tel(mem_tel);
		memVo.setMem_addr(mem_addr);
		
		int res = service.insertMember(memVo);
		
		if (res == 1) {
			printTitle("자료 추가 성공");
		} else {
			printTitle("자료 추가 실패");
		}
	}
	
	private String input(String text) {
		System.out.print(text);
		return scan.nextLine().trim();
	}

	private void printTitle(String... text) {
		System.out.println();
		System.out.println("==========================================================");
		for(int i = 0; i < text.length; i ++) {
			System.out.println(text[i]);
		}
		System.out.println("==========================================================");
	}
	
	private String printMenu() {
		System.out.println();
		System.out.println("==========================================================");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 한개만 수정");
		System.out.println("0. 작업 끝");
		System.out.println("==========================================================");
		System.out.print("작업 입력 >> ");

		return scan.nextLine();
	}

}
