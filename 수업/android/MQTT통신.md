### MQTT 통신



mosquitto를 깔고 환경변수를 추가한 후에 cmd창에

![](C:\Users\student\Desktop\til\til\수업\arduino\1.PNG)

다른 cmd창 치운후에 

입력하면 처음 창에 추가된다.

![2](C:\Users\student\Desktop\til\til\수업\arduino\images\2.PNG)

또 다른 cmd창을 켜서

![image-20200519103859655](C:\Users\student\Desktop\til\til\수업\arduino\images\9)

확인

![image-20200519103921620](C:\Users\student\Desktop\til\til\수업\arduino\images\8)



또다른 cmd창

![image-20200519104220993](C:\Users\student\Desktop\til\til\수업\arduino\images\7)

![image-20200519104333217](C:\Users\student\Desktop\til\til\수업\arduino\images\6)

iot sub 한곳에  가서 

```bash
C:\Users\student>mosquitto_sub -h 70.12.116.63 -t iot -p 1883
```



![image-20200519105111584](C:\Users\student\Desktop\til\til\수업\arduino\images\5)



이후

![image-20200519105154804](C:\Users\student\Desktop\til\til\수업\arduino\images\3)

이렇게 보내면 



결과값이 보내지게 된다.

![image-20200519105235056](C:\Users\student\Desktop\til\til\수업\arduino\images\4)

