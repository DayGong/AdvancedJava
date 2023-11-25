package kr.or.ddit.basic;

import java.util.Arrays;

public class ThreadTest19 {
	public static void main(String[] args) {
		DataBox box = new DataBox();
		
		SaveTestThread saveTest = new SaveTestThread(box);
		ReadTestThread readTest = new ReadTestThread(box);
		
		saveTest.start();
		readTest.start();
	}
}

// 데이터를 저장하는 쓰레드 ==> setValue()메서드만 호출하는 쓰레드
class SaveTestThread extends Thread {
	private DataBox databox;

	public SaveTestThread(DataBox databox) {
		this.databox = databox;
	}

	@Override
	public void run() {                                                                                                                                                                                                                                                                                                                                                                                           
		String[] data = { "이순신", "강감찬", "이몽룡", "홍길동" };

		for (int i = 0; i < data.length; i++) {
			databox.setValue(data[i]);
		}
	}
}

// 데이터를 읽어가는 쓰레드 ==> getValue()메서드만 호출하는 쓰레드
class ReadTestThread extends Thread {
	private DataBox databox;

	public ReadTestThread(DataBox databox) {
		this.databox = databox;
	}
	
	@Override
	public void run() {
		String[] readData = new String[4];
		
		for (int i = 0; i < readData.length; i++) {
			readData[i] = databox.getValue();
		}
		
		System.out.println(Arrays.toString(readData));
	}
}

// 데이터를 공통으로 사용하는 클래스
class DataBox {
	private String value;

	// getter, setter
	// value값이 null이면 value변수에 문자열이 저장될 때까지 기다리고
	// value변수에 값이 있으면 해당 문자열을 반환한다.
	// 반환 후에는 value변수를 null로 만든다.
	public synchronized String getValue() {
		if (value == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		// value변수가 null이 아닐 때
		String temp = value;
		System.out.println("쓰레드가 읽어간 데이터: " + temp);
		value = null;

		notify();

		return temp;
	}

	// value변수에 값이 있으면 value변수가 null이 될 때까지 기다린다.
	// value변수가 null이 되면 새로운 데이터를 value변수에 저장한다.
	public synchronized void setValue(String value) {
		if (this.value != null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		// value가 null일 때
		this.value = value;
		System.out.println("Thread가 새로 저장한 데이터: " + value);

		notify();
	}
}