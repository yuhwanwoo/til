### person 클래스



```python
package oop.chap06;
//클래스를 정의하고 멤버변수를 정의하는 방법
//Person 클래스를 사용하는 클래스
//멤버변수를 정의할때는 특별한 경우를 제외하고 초기값을 주지 않는다.
//초기값을 주지 않아도 참조형은 null, 정수형은 0, 실수형은 0.0, boolean은 false
//멤버변수를 정의할 때 접근 제어자를 추가해서 접근을 제어할 수 있다.
//public
//default
//protected
//private
//클래스를 정의할때 멤버변수는 private으로 선언해서 외부에서 접근할 수 없도록 정보를 은닉하고
//public 메소드를 통해 접근할 수 있도록 구현한다.
public class Person {
	String name; //클래스의 특성   =>멤버변수 or 필드
	String addr; // 기본형 참조형 모두가능
	int age;
	
	//모든 멤버변수는 private로 선언되어 있기 때문에 값을 설정하는 메소드를
	//값을 가져올 수 있는 메소드가 필요하다.
	//이런 역할을 하는 메소드를 정의하는 경우
	
	//name변수의 값을 설정하는 메소드
	//메소드명 : set + 멤버변수명(단, 첫글자를 대문자로 바꾼 멤버변수명)
	//		  setName
	public void setName(String name) {   //setter메소드
		this.name=name; // this는 현재 내가 갖고 있는 객체에 넣을거야
	}
	
	//name변수를 지정한 값을 호출하는 곳으로 넘겨줄 메소드
	//메소드명 get+멤버변수명(단, 첫글자를 대문자로 바꾼)
	//		getName
	public String getName() { //getter 메소드
		return this.name; // 그냥 name해도 똑같지만 명확한 명시를 위해 this
	}
	
	public void setAddr(String addr) {   //setter메소드
		this.addr=addr; // this는 현재 내가 갖고 있는 객체에 넣을거야
	}
	
	public String getAddr() { //getter 메소드
		return this.addr; // 그냥 name해도 똑같지만 명확한 명시를 위해 this
	}
	
	public void setAge(int age) {   //setter메소드
		this.age=age; // this는 현재 내가 갖고 있는 객체에 넣을거야
	}
	
	public int getAge() { //getter 메소드
		return this.age; // 그냥 name해도 똑같지만 명확한 명시를 위해 this
	}
	
}

```



### 기존방법과 set과 get을 이용한 함수 호출

```python
package oop.chap06;
//Person 클래스를 사용하는 클래스
// 멤버변수를 정의할때는 특별한 경우를 제외하고 초기값을 주지 않는다.
// 초기값을 주지 않아도 참조형은 null, 정수형은 0, 실수형은 0.0, boolean은 false
//
public class PersonTest {
	public static void main(String[] args) {
		Person p1=new Person();
		p1.setName("장동건");
		p1.addr="서울";
		p1.setAddr("서울");
		p1.age=14; //age변수는 private으로 선언되어있으므로 접근할 수 없다.
		System.out.println("성명:"+p1.name);
		System.out.println("성명:"+p1.getName());
		
		System.out.println("주소:"+p1.addr);
		System.out.println("성명:"+p1.getAddr());
		
		System.out.println("나이:"+p1.age);
		System.out.println("성명:"+p1.getAge());
		
		
		Person p2=new Person();
		p2.name="김서연";
		p2.addr="인천시";
		p2.age=25;
		System.out.println("성명:"+p2.name);
		System.out.println("주소:"+p2.addr);
		System.out.println("나이:"+p2.age);
	
	}

}

```

