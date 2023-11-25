package kr.or.ddit.basic;

import java.util.ResourceBundle;

/*
	ResourceBundle객체
	==> 파일의 확장자가 '.properties'인 파일의 내용을 읽어와 key값과 value값을 분리해서 정보를 갖는 객체
	(properties파일을 처리하는 전용 객체)
 */
public class ResourceBundleTest {

	public static void main(String[] args) {
		// ResourceBundle객체를 이용하여 파일 내용 읽어오기
		
		// ResourceBundle객체 생성
		// ==> 객체를 생성할 때 읽어올 파일을 지정한다.
		// (읽어올 파일의 경로를 패키지 형태로 지정하고 마지막에 확장자는 붙이지 않는다.
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		// 읽어온 내용 출력하기
		System.out.println("driver >> " + bundle.getString("driver"));
		System.out.println("url    >> " + bundle.getString("url"));
		System.out.println("user   >> " + bundle.getString("user"));
		System.out.println("pass   >> " + bundle.getString("pass"));
	}
	
}
