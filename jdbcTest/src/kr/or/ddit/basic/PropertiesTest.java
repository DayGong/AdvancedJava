package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// 확장자가 '.properties'인 파일의 내용을 읽어오는 예제
public class PropertiesTest {

	public static void main(String[] args) {
		
		// key값과 value값이 String형식으로만 저장 가능한 객체
		Properties prop = new Properties();	// Properties객체 생성
		
		// 읽어올 파일 정보를 갖는 File객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		
		// 파일 입력용 스트림 객체 변수 선언
		FileInputStream fin = null;
		
		try {
			// 스트림 객체 생성
			fin = new FileInputStream(f);
			
			// 입력 스트림을 이용하여 파일 내용을 읽어와 Properties객체에 저장
			prop.load(fin);	// 읽어온 파일의 key값과 value값을 분리해서 Properties객체에 저장
			
			// 읽어온 파일 내용 출력
			System.out.println("driver ==> " + prop.getProperty("driver"));
			System.out.println("url    ==> " + prop.getProperty("url"));
			System.out.println("user   ==> " + prop.getProperty("user"));
			System.out.println("pass   ==> " + prop.getProperty("pass"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fin != null) try { fin.close(); } catch(IOException e) {}
		}
	}

}
