package kr.or.ddit.basic.homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
	회원 관리를 하는 프로그램을 작성하시오. (MYMEMBER테이블 이용)

	아래 메뉴의 기능을 모두 구현하시오. (CRUD기능 구현하기)

	메뉴 예시)
	------------------
	1. 자료 추가 - insert (C)
	2. 자료 삭제 - delete (D)
	3. 자료 수정 - update (U)
	4. 전체 자료 출력 - select (R)
	0. 작업 끝
	-----------------

	조건)
	1) 자료 추가 기능에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받기)
	2) 자료 삭제는 '회원 ID'를 입력 받아 처리한다.
	3) 자료 수정에서 '회원ID'는 변경되지 않는다.
*/

public class JdbcTest06 {
	Scanner scan = new Scanner(System.in);

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest06().MemStart();
	}

	private void MemStart() {
		while (true) {
			String choice = printMenu();

			switch (choice) {
			case "1":
				insert();
				break;
			case "2":
				delete();
				break;
			case "3":
				update();
				break;
			case "4":
				selectAll();
				break;
			case "0":
				printTitle("작업을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	private void update() {
		printTitle("자료 수정");
		
		String id;
		int count;
		
		do {
			id = input("수정할 MEM_ID >> ");
			count = selectCount(id);

			if (count < 1) {
				System.out.println("없는 ID입니다.");
				System.out.println("다시 입력해주세요.");
				System.out.println();
			}

		} while (count < 1);
		
		String changeSql = changeYesNo("MEM_PASS") + changeYesNo("MEM_NAME")
				+ changeYesNo("MEM_TEL") + changeYesNo("MEM_ADDR");
		
		if (changeSql == null || "".equals(changeSql)) {
			printTitle("정보 수정을 취소합니다.");
			return;
		} else {
			changeSql = changeSql.substring(0, changeSql.length() - 2);
		}
		
		String sql = " update MYMEMBER set " + changeSql + " where MEM_ID = ?";
		
		int res = updateDB(sql, id);
		
		if (res == 1) {
			printTitle("자료 수정 성공");
		} else {
			printTitle("자료 수정 실패");
		}
	}
	
	private String changeYesNo(String text) {
		while(true) {
			String str = "";
		
			System.out.print(text + "를 변경하시겠습니까?(y/n) >> ");
			String yesNo = scan.next().toLowerCase();
		
			switch(yesNo) {
			case "y":
				String memColumn = input("변경할 " + text + " >> "); 
				str = text + " = '" + memColumn + "', ";
				break;
			case "n":
				break;
			default:
				System.out.println("다시 입력해주세요.");
			}
			return str;
		}
	}

	private void delete() {
		printTitle("자료 삭제");
		
		String memId;
		int count;
		
		do {
			memId = input("삭제할 MEM_ID >> ");
			count = selectCount(memId);

			if (count < 1) {
				System.out.println("없는 ID입니다.");
				System.out.println("다시 입력해주세요.");
				System.out.println();
			}

		} while (count < 1);
		
		String sql = " delete from MYMEMBER where MEM_ID = ? ";
		
		int res = updateDB(sql, memId);
		
		if (res == 1) {
			printTitle("자료 삭제 성공");
		} else {
			printTitle("자료 삭제 실패");
		}
	}

	private void selectAll() {
		printTitle("전체 자료 출력");
		
		printTitle("MEM_ID\tMEM_PASS\tMEM_NAME\tMEM_TEL\t\tMEM_ADDR");
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = " select * from MYMEMBER ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			
			while(rs.next()) {
				count++;
				System.out.print(rs.getString("MEM_ID") + "\t");
				System.out.print(rs.getString("MEM_PASS") + "\t\t");
				System.out.print(rs.getString("MEM_NAME") + "\t\t");
				System.out.print(rs.getString("MEM_TEL") + "\t");
				System.out.println(rs.getString("MEM_ADDR"));
			}
			
			if(count == 0) {
				System.out.println("등록된 회원 정보가 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( rs != null ) try { rs.close(); } catch ( SQLException e ) {}
			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
		}
	}

	private void insert() {
		printTitle("자료 추가");

		String id;
		int count;
		
		do {
			id = input("MEM_ID >> ");
			count = selectCount(id);

			if (count > 0) {
				System.out.println("중복된 ID입니다.");
				System.out.println("다시 입력해주세요.");
				System.out.println();
			}

		} while (count > 0);

		String passwd = input("MEM_PASS >> ");
		String name = input("MEM_NAME >> ");
		String tel = input("MEM_TEL >> ");
		String addr = input("MEM_ADDR >> ");
			
		String inSql = "insert into MYMEMBER "
				+ "(mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
				+ " values (?, ?, ?, ?, ?)";

		int res = updateDB(inSql, id, passwd, name, tel, addr);
			
		if (res == 1) {
			printTitle("자료 추가 성공");
		} else {
			printTitle("자료 추가 실패");
		}

	}
	
	private int updateDB(String sql, String... memData) {
		int result = 0;
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < memData.length; i++) {
				pstmt.setString((i+1), memData[i]);
			}
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("UPDATE문 실패");
			e.printStackTrace();
		} finally {
			if ( rs != null ) try { rs.close(); } catch ( SQLException e ) {}
 			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
		}
		
		return result;
	}

//	private ResultSet selectDB(String sql) {
//		try {
//			conn = DBUtil.getConnection();
//
//			pstmt = conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery();
//			
//		} catch (SQLException e) {
//			System.out.println("SELECT 실패");
//			e.printStackTrace();
//		}
//		
//		return rs;
//	}
	
	private int selectCount(String id) {
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();

			String sql = " select count(*) CNT from MYMEMBER "
					+ "where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("CNT");
			}
			
		} catch (SQLException e) {
			System.out.println("SELECT 실패");
			e.printStackTrace();
		} finally {
			if ( rs != null ) try { rs.close(); } catch (SQLException e) {}
			if ( pstmt != null ) try { pstmt.close(); } catch ( SQLException e ) {}
			if ( conn != null ) try { conn.close(); } catch ( SQLException e ) {}
		}
		
		return count;
	}
	
	private String input(String text) {
		System.out.print(text);
		return scan.nextLine().trim();
	}

	private void printTitle(String text) {
		System.out.println();
		System.out.println("==========================================================");
		System.out.println(text);
		System.out.println("==========================================================");
	}

	private String printMenu() {
		System.out.println();
		System.out.println("==========================================================");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.println("==========================================================");
		System.out.print("작업 입력 >> ");

		return scan.next();
	}

}
