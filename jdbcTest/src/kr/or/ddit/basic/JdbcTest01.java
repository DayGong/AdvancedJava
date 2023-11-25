package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest01 {
	public static void main(String[] args) {
		// DB 작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결 => Connection 객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", // url 정보 
					"pc18",  // user ID 
					"java"); // password
			
			// 3. 질의
			// 3-1. SQL문을 작성한다.
			String sql = " select * from lprod ";
			
			// 3-2. Statement객체 생성 => 질의하는 객체 생성
			// Connection객체를 이용하여 생성한다.
			stmt = conn.createStatement();
			
			// 3-3. SQL문을 서버로 보내서 처리된 결과를 얻어온다.
			// 실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환된다.
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 처리하기 => 한 레코드씩 화면에 출력하기
			// ResultSet객체에 저장된 데이터를 차례로 꺼내와서 처리해야 한다.
			// 이 때는 반복문과 ResultSet객체의 next()메서드를 이용해 처리한다.
			
			System.out.println("============= 검색 결과 =============");
			
			// ResultSet객체변수.next()
			// ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드로 이동시키고
			// 그곳에 데이터가 있으면 true, 없으면 false 반환
			while(rs.next()) {
				// 포인터가 가리키는 곳의 데이터(데코드)를 가져와서 처리한다.
				
				// 포인터가 가리키는 곳의 데이터를 가져오는 방법
				// 형식1) ResultSet객체변수.get자료형이름("컬럼명 또는 alias명");
				// 형식2) ResultSet객체변수.get자료형이름(컬럼번호); // 컬럼번호는 1부터 시작
				
				System.out.println("LPROD_ID: " + rs.getInt("lprod_id"));
				System.out.println("LPROD_GU: " + rs.getString(2));
				System.out.println("LPROD_NM: " + rs.getString("lprod_nm"));
				System.out.println("-----------------------------------------");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 5. 자원 반납
			if(rs != null) try { rs.close(); } catch (SQLException e) {	}
			if(stmt != null) try { stmt.close(); } catch (SQLException e) {	}
			if(conn != null) try { conn.close(); } catch (SQLException e) {	}
		}
	}
}
