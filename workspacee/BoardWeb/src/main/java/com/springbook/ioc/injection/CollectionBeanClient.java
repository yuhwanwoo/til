<<<<<<< HEAD
package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {

	public static void main(String[] args) {
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean =(CollectionBean)factory.getBean("collectionBean");
		Map<String,String> addressList = bean.getAddressList();
		/*for(String address:addressList) {
			System.out.println(address.toString());
		}*/
		factory.close();
	}

}
=======
package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {

	public static void main(String[] args) {
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean =(CollectionBean)factory.getBean("collectionBean");
		Map<String,String> addressList = bean.getAddressList();
		/*for(String address:addressList) {
			System.out.println(address.toString());
		}*/
		factory.close();
	}

}
>>>>>>> ed3138fd25c5bf4676156d4e59c59591e4b42fea
