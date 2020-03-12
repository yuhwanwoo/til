<<<<<<< HEAD
package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		
		
		//1. Spring 컨테이너를 구동한다.
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring 컨테이너로부터 필요한 객체를 요청(Lookup)한다.
		TV tv=(TV)factory.getBean("tv");
		
		tv.volumeDown();
		// 3. Spring 컨테이너를 종료한다.
		factory.close();
	}

}
=======
package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		
		
		//1. Spring 컨테이너를 구동한다.
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. Spring 컨테이너로부터 필요한 객체를 요청(Lookup)한다.
		TV tv=(TV)factory.getBean("tv");
		
		tv.volumeDown();
		// 3. Spring 컨테이너를 종료한다.
		factory.close();
	}

}
>>>>>>> ed3138fd25c5bf4676156d4e59c59591e4b42fea
