package polymorphism;

import org.springframework.stereotype.Component;

@Component("sony") //component를 선언하면 무조건 기본생성자는 생성되는듯
public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("===> SonySpeaker 객체 생성");
	}
	public void volumeUp() {
		System.out.println("SonySpeaker---소리 올린다.");
	}
	public void volumeDown() {
		System.out.println("SonySpeaker---소리 내린다.");
	}
}
