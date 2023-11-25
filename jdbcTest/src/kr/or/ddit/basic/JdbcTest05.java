package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	LPROD테이블에 새로운 데이터를 추가하기
 	lprod_gu와 lprod_nm은 직접 입력 받아서 처리하고,
 	lprod_id는 현재의 lprod_id 중에서 제일 큰 값보다 1 크게 한다.
 	
 	입력 받은 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다. 
 */

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//	      Statement stmt = null;

		try {
//	         Class.forName("oracle.jdbc.driver.OracleDriver");

//	         conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc18", "java");

			conn = DBUtil.getConnection();

			// lprod_id는 현재의 lprod_id 중에서 제일 큰 값보다 1 크게 한다.
			String sql = "select max(lprod_id) maxId from lprod "; // maxId를 추가 작성하여 이름

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			int maxId = 0;
			if (rs.next()) {
				rs.getInt(1); // 방법1
//	             rs.getInt("maxid");   // 방법2
			}
			maxId++;
			// ---------------------------------------------

			// 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			String gu; // lprod_gu(상품분류코드)값이 저장될 변수 선언
			int count = 0; // 입력한 lprod_gu의 개수가 저장될 변수 선언

			String sql2 = "select count(*) cnt from lprod where lprod_gu = ? ";
			pstmt = conn.prepareStatement(sql2); // 생성할때 쿼리를 미리 해석해놈 - 데이터만 넣어줌
			// executeQuery 는 실행될때 쿼리를 해석함

			do {
				System.out.print("상품 분류 코드 (LPROD_GU) 입력 >> ");
				gu = scan.next();

				pstmt.setString(1, gu); // 데이터 셋팅

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");

				}
				if (count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요.");
					System.out.println();

				}

			} while (count > 0); // 0 보다 크면 데이터가 있다는 뜻
			// ---------------------------------------------
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();

			String sql3 = "insert into lprod (lprod_id, lprod_gu,lprod_nm)" + "values(?, ?, ?)";

			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxId);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("등록 성공 !!");
			} else {
				System.out.println("등록 실패 ~~");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
}

//public class JdbcTest05 {
//	Scanner scan = new Scanner(System.in);
//	
//	Connection conn = null;
//	Statement stmt = null;
//	ResultSet rs = null;
//	PreparedStatement pstmt = null;
//	
//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	String pcName = "pc18";
//	String pcPasswd = "java";
//	
//	List<String> lpList;
//	
//	public static void main(String[] args) {
//		new JdbcTest05().DbStart();
//	}
//	
//	void DbStart() {
//		lpList = inputLprod();
//		
//		String numSql = " select max(lprod_id) from lprod ";
//		
//		int maxNum = select("max(lprod_id)", numSql);
//		
//		String inSql = " insert into lprod (lprod_id, lprod_gu, lprod_nm) "
//				+ " values ( ?, ?, ?) ";
//		
//		int result = insert(maxNum, lpList, inSql);
//		
//		if (result == 1) {
//			System.out.println("데이터 업데이트 성공");
//		} else {
//			System.out.println("데이터 업데이트 실패");
//		}
//	}
//	
//	List<String> inputLprod() {
//		String lpGu = "";
//		String lpNm = "";
//		
//		while (true) {
//			System.out.println("LPROD테이블에 새로운 데이터 추가하기");
//			System.out.print("구분 번호 >> ");
//			lpGu = scan.next();
//				
//			String dupliSql = " select count(*) from lprod "
//					+ " where lprod_gu = '" + lpGu + "' ";
//			
//			int count = select("count(*)", dupliSql);
//			
//			if (count == 0) {
//				break;
//			}
//			
//			System.out.println("값이 중복되었습니다.");
//			System.out.println("다시 입력해주세요.");
//				
//		}
//		
//		System.out.print("제품 이름 >> ");
//		lpNm = scan.next();
//		
//		lpList = new ArrayList<String>();
//		
//		lpList.add(lpGu);
//		lpList.add(lpNm);
//		
//		return lpList;
//	}
//	
//	int select(String col, String sql) {
//		int count = 0;
//		
//		try {
//			// Class.forName("oracle.jdbc.driver.OracleDriver");
//	
//			// conn = DriverManager.getConnection(url, pcName, pcPasswd);
//
//			conn = DBUtil.getConnection();

//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) {
//				count = rs.getInt(col);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			if ( rs != null ) try { rs.close(); } catch (SQLException e) {}
//			if ( stmt != null ) try { stmt.close(); } catch (SQLException e) {}
//			if ( conn != null ) try { conn.close(); } catch (SQLException e) {}
//		}
//		
//		return count;
//	}
//	
//	int insert(int maxNum, List<String> lpList, String sql) {
//		int count = 0;
//		
//		try {
//			// Class.forName("oracle.jdbc.driver.OracleDriver");
//	
//			// conn = DriverManager.getConnection(url, pcName, pcPasswd);
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setInt(1, maxNum + 1);
//			pstmt.setString(2, lpList.get(0));
//			pstmt.setString(3, lpList.get(1));
//			
//			count = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			if ( rs != null ) try { rs.close(); } catch (SQLException e) {}
//			if ( pstmt != null ) try { pstmt.close(); } catch (SQLException e) {}
//			if ( conn != null ) try { conn.close(); } catch (SQLException e) {}
//		}
//		
//		return count;
//	}
//}
