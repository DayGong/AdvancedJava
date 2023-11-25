package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
Map
key값과 value값을 한 쌍으로 관리하는 객체
key값은 중복을 허용하지않고 순서(index)가 없다.(Set의 특징을 갖는다.)
value값은 중복을 허용한다.
*/
public class MapTest {
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		// 자료 추가 ==> put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1111-2222");
		
		System.out.println("map ==> " + map);
		
		// 자료 수정 ==> 데이터를 추가할 때 key값이 같으면 나중에 추가한 값이 저장된다.
		map.put("addr", "서울");
		
		// 자료 삭제 ==> remove(삭제할 key값) : 매개변수에 주어진 key값을 찾아서 삭제한다.
		// 반환값: 삭제된 자료의 value값
//		String delTel = map.remove("tel");
//		System.out.println("삭제된 데이터의 value값 >> " + delTel);
//		System.out.println("map ==> " + map);
		
		// 자료 읽기 ==> get(key값) : 매개변수에 주어진 key값과 짝이 되는 value 값을 반환
		System.out.println("이름: " + map.get("name"));
		System.out.println();
		
		// key값이 존재하는지 여부를 나타내는 메서드 ==> containsKey(key값)
		// 매개변수에 주어진 key값이 있으면 true, 없으면 false
		System.out.println("tel 키값의 존재 여부: " + map.containsKey("tel"));
		System.out.println("age 키값의 존재 여부: " + map.containsKey("age"));
		
		// Map에 저장된 모든 데이터들을 읽어와 사용하는 방법
		// 방법1) 모든 key값을 읽어와서 처리하기 ==> keySet()메서드를 이용한다.
		// keySet()메서드 ==> Map의 모든 key값들을 읽어와 Set형으로 반환
		Set<String> keySet = map.keySet();
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("======================================");
		
		// 향상된 for문 이용
		for (String key : map.keySet()) {
			String value = map.get(key);
			System.out.println(key + " ==> " + value);
		}
		System.out.println("======================================");
		
		// 방법2) 모든 value값들만 읽어와서 처리하기 ==> values()
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("======================================");
	}
}
