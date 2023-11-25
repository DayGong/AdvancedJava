package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataIOTest02 {
	public static void main(String[] args) {
		try {
			// DataOutputStream으로 출력했던 데이터 읽어오기
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			DataInputStream din = new DataInputStream(fin);
			
			// DataInputStream으로 자료를 읽어올 때는
			// 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			System.out.println("정수형: " + din.readInt());
			System.out.println("실수형: " + din.readFloat());
			System.out.println("논리형: " + din.readBoolean());
			System.out.println("문자열: " + din.readUTF());
			
			System.out.println();
			System.out.println("읽기 작업 완료");
			
			// 스트림 닫기
			din.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
