package kr.or.ddit.basic.homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	문제)
	'D:/d_other' 폴더에 있는 '펭귄.jpg' 파일을
	'D:/d_other/연습용' 폴더에 '복사본_펭귄.jpg' 파일로 복사하는 프로그램을 작성
 */

public class FileCopy {
	public static void main(String[] args) {
		File file = new File("d:/d_other/연습용");

		System.out.println(file.getName() + "의 존재 여부: " + file.exists());

		if (!file.exists()) {
			System.out.println(file.getName() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
		}

		try {
			// 복사할 원본 파일을 처리할 스트림 객체 생성
			FileInputStream picture = new FileInputStream("d:/d_other/펭귄.jpg");
			BufferedInputStream bi = new BufferedInputStream(picture);

			// 복사될 대상 파일을 처리할 스트림 객체 생성
			FileOutputStream copyPicture = 
					new FileOutputStream("d:/d_other/연습용/복사본_펭귄.jpg");
			BufferedOutputStream bo = new BufferedOutputStream(copyPicture);

			System.out.println("복사 시작");
			
			int data;

			while ((data = bi.read()) != -1) {
				bo.write(data);
			}

			bo.flush();

			bi.close();
			bo.close();
			picture.close();
			copyPicture.close();
			
			System.out.println("복사 작업 완료");

		} catch (IOException e) {
		}
	}
}
