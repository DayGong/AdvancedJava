package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오
public class JdbcTest02 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String pcName = "pc18";
		String pcPasswd = "java";
		
		System.out.print("LPROD_ID 값을 입력해주세요 >> ");
		int userIn = scan.nextInt();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, pcName, pcPasswd);
			
			stmt = conn.createStatement();
			
//			String sql = " select * from lprod ";
			String sql = " select * from lprod where lprod_id > " + userIn;
			rs = stmt.executeQuery(sql);
			
//			"으앙아 너이렇게 꾸미면 나는 어떡하라고. 나는 완전 판잣집인데. 나는 어떡하라고."
			System.out.println("================== 검색 결과 ==================");
			System.out.println("LPROD_ID\tLPROD_GU\tLPROD_NM");
			System.out.println("===========================================");
			
			while(rs.next()) {
//				if (rs.getInt("lprod_id") > userIn) {
					System.out.print(rs.getInt("lprod_id")+"\t\t");
					System.out.print(rs.getNString("lprod_gu")+"\t\t");
					System.out.println(rs.getString("lprod_nm"));
//				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		
	}
	
}
