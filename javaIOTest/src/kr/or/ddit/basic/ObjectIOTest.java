package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {
	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("홍길서", 30, "서울");
		Member mem3 = new Member("홍길남", 40, "인천");
		Member mem4 = new Member("홍길북", 50, "천안");
		
		try {
			
			// 객체를 파일에 저장
			FileOutputStream fout = new FileOutputStream("d:/d_other/memObj.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			// 저장 작업(쓰기 작업)
			oout.writeObject(mem1);
			oout.writeObject(mem2);
			oout.writeObject(mem3);
			oout.writeObject(mem4);
			oout.writeObject(null); // 객체를 저장할 때 마지막에 null을 저장하면
									// 이 데이터를 읽어올 때 발생하는 EOFException을 방지할 수 있다.
			
			System.out.println("객체 저장 작업 끝");
			
			oout.close(); // 스트림 닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 저장된 객체를 읽어와 그 객체의 내용을 화면에 출력
		// 입력용 스트림 객체 생성
		try {
			ObjectInputStream oin = new ObjectInputStream(
						new BufferedInputStream(
						new FileInputStream("d:/d_other/memObj.dat")));
			
			Object obj; // 읽어온 객체가 저장될 변수
			
			System.out.println("객체 읽기 작업 시작");
			System.out.println("=================================");
			
			// readObject()메서드는 데이터를 긑까지 다 읽어오면 EOFException을 발생시킨다.
			while( (obj = oin.readObject()) != null) {
				// 읽어온 데이터를 원래의 자료형으로 형변환 후 사용한다.
				Member mem = (Member)obj;
				
				System.out.println("이름: " + mem.getName());
				System.out.println("나이: " + mem.getAge());
				System.out.println("주소: " + mem.getAddr());
				System.out.println("=================================");
			}
			
			oin.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Member implements Serializable {
	
	// transient ==> 직렬화가 되지 않을 멤버변수에 지정한다.
	
	private String name;
	private transient int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
}