package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String pcName = "pc18";
		String pcPasswd = "java";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, pcName, pcPasswd);

			System.out.println("계좌번호 정보 추가하기");
			System.out.print("계좌번호 >> ");
			String bankNo = scan.next();

			System.out.print("은행이름 >> ");
			String bankName = scan.next();

			System.out.print("예금주 이름 >> ");
			String userName = scan.next();

			// Statement객체를 이용하여 데이터 추가하기
//			String sql = " insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) " 
//			+ " values ( '" + bankNo + "', '" + bankName + "', '" + userName + "', SYSDATE) ";

//			System.out.println(sql);

//			stmt = conn.createStatement();

			// SQL문 실행하기
			// ==> SQL문이 select문일 때에는 executeQuery()메서드를 사용하고,
			// SQL문이 insert, update, delete등과 같이 select문이 아닐 때에는
			// executeUpdate()메서드를 사용한다.
			
			// executeQuery()메서드의 반환값 => 작업에 성공한 레코드 수
//			int cnt = stmt.executeUpdate(sql);

			// PreparedStatement객체를 이용하여 추가하기
			// SQL문을 작성할 때 SQL문 안에 데이터가 들어갈 자리에는 물음표(?)로 표시하여 작성한다.
			String sql = " insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) " 
					+ " values ( ?, ?, ?, SYSDATE) ";

			// PreparedStatement객체를 생성한다.
			// 이 때 실행할 SQL문을 지정하여 생성한다.
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?) 자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표의 순번, 데이터);
			// 물음표의 순번은 1부터 시작
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			// 데이터의 셋팅이 완료되면 SQL문을 실행한다.
			// 괄호 안에 아무것도 쓰지 않는다.
			int cnt = pstmt.executeUpdate();
			System.out.println("반환값 cnt = " + cnt);
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if ( stmt != null ) try { stmt.close(); } catch (SQLException e) {}
			if ( pstmt != null ) try { pstmt.close(); } catch (SQLException e) {}
			if ( conn != null ) try { conn.close(); } catch (SQLException e) {}
		}
	}
}
