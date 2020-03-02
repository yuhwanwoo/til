package polymorphism;

import org.springframework.stereotype.Component;

@Component("apple") //component를 선언하면 무조건 기본생성자는 생성되는듯
public class AppleSpeaker implements Speaker{
	public AppleSpeaker() {
		System.out.println("===> AppleSpeaker 객체 생성");
	}
	public void volumeUp() {
		System.out.println("AppleSpeaker---소리 올린다.");
	}
	public void volumeDown(){
		System.out.println("AppleSpeaker---소리 올린다.");
	}
}
