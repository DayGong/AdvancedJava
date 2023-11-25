package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {
	public static void main(String[] args) {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		byte[] temp = new byte[4]; // 입출력에 활용할 배열 생성

		// 입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);

		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			// available() ==> 앞으로 읽어올 수 있는 데이터의 개수를 반환
			while (input.available() > 0) {
//				input.read(temp);   // temp배열 개수만큼 읽어와서 temp배열에 저장
//				output.write(temp); // temp배열의 모든 자료를 출력
				
				int len = input.read(temp); // 실제 읽어온 데이터의 개수를 반환
				
				// temp배열의 데이터 중에서 0번째부터 len개의 데이터를 출력
				output.write(temp, 0, len); 
				
				System.out.println("반복문 안 temp ==> " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			
			System.out.println("inSrc  ==> " + Arrays.toString(inSrc));
			System.out.println("outSrc ==> " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
		} catch (IOException e) {
		}
	}
}
