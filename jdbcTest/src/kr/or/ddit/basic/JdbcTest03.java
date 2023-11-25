package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) Lprod_ID값을 2개 입력 받아 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오

public class JdbcTest03 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String pcName = "pc18";
		String pcPasswd = "java";
		
		System.out.print("첫번째 LPROD_ID 값을 입력해주세요 >> ");
		int num1 = scan.nextInt();
		
		System.out.print("두번째 LPROD_ID 값을 입력해주세요 >> ");
		int num2 = scan.nextInt();
		
		int small, big;
		small = Math.min(num1, num2);
		big = Math.max(num1, num2);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, pcName, pcPasswd);
			stmt = conn.createStatement();
			
			String sql = " select * from lprod "
					+ " where lprod_id between " + small + " and " + big;
//			String sql = " select * from lprod "
//					+ " where lprod_id lprod_id >= " + small + " and lprod_id <= " + big;
			
//			if (num1 < num2) {
//				sql = " select * from lprod where lprod_id between " + num1 + " and " + num2;
//			} else {
//				sql = " select * from lprod where lprod_id between " + num2 + " and " + num1;
//			}
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("================== 검색 결과 ==================");
			System.out.println("LPROD_ID\tLPROD_GU\tLPROD_NM");
			System.out.println("===========================================");
			
			while(rs.next()) {
				System.out.print(rs.getInt("lprod_id") + "\t\t");
				System.out.print(rs.getString("lprod_gu") + "\t\t");
				System.out.println(rs.getString("lprod_nm"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (stmt != null ) try { stmt.close(); } catch (SQLException e) {}
			if (conn != null ) try { conn.close(); } catch (SQLException e) {}
		}
	}
}
