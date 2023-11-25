package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	public static void main(String[] args) {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		// 입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);

		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		int data; // 읽어온 자료가 저장될 변수

		// read()메서드 => 더이상 읽어올 자료가 없으면 -1을 반환
		while ((data = input.read()) != -1) { // 자료 읽기
			// 읽어온 자료를 처리하는 코드를 작성
			output.write(data); // 자료 출력
		}

		// 스트림에 출력된 값들을 배열로 변환해서 가져오기
		outSrc = output.toByteArray();

		// 스트림을 모두 사용한 후에는 close()메서드를 사용해 자원을 반납해줘야 한다.
		try {
			input.close();
			output.close();
		} catch (IOException e) {
		}
		
		System.out.println("inSrc  ==> " + Arrays.toString(inSrc));
		System.out.println("outSrc ==> " + Arrays.toString(outSrc));
	}
}
