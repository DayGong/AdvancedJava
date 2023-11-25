package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {
	/*
	- Properties 객체 ==> Map보다 축소된 기능의 객체라고 할 수 있다.
	
	- Map은 key값과 value 값에 모든 형태의 객체를 사용할 수 있지만 
	  Properties는 key값과 value값에 String만 사용할 수 있다.
	
	- Map은 put(), get() 메서드를 이용하여 데이터를 입출력하지만 
	  Properties는 setProperty(), getProperty()메서드를 이용하여 입출력한다.
	  
	- Properties객체는 데이터를 파일로 입출력할 수 있는 기능이 있다.
	 */
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		int age = 25;
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "30");
		prop.setProperty("age2", age + "");
		prop.setProperty("age3", String.valueOf(age));
		prop.setProperty("addr", "대전시");
		
		String name = prop.getProperty("name");
		int age2 = Integer.parseInt(prop.getProperty("age2"));
		String addr = prop.getProperty("addr");
		
		System.out.println("이름: " + name);
		System.out.println("나이: " + age2);
		System.out.println("주소: " + addr);
	}
}
