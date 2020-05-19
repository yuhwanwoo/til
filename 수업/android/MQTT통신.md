### MQTT 통신



mosquitto를 깔고 환경변수를 추가한 후에 cmd창에

![1](https://user-images.githubusercontent.com/22831002/82285417-641e9b80-99d6-11ea-84e1-8a48a2e03aa2.PNG)



다른 cmd창 치운후에 

입력하면 처음 창에 추가된다.

![2](https://user-images.githubusercontent.com/22831002/82285507-97612a80-99d6-11ea-8769-4d251c2f6620.PNG)



또 다른 cmd창을 켜서

![9](https://user-images.githubusercontent.com/22831002/82285519-9af4b180-99d6-11ea-878e-fe0ee3bb64e3.png)

확인

![8](https://user-images.githubusercontent.com/22831002/82285516-9a5c1b00-99d6-11ea-9c49-4934d31655ea.png)



또다른 cmd창

![7](https://user-images.githubusercontent.com/22831002/82285515-99c38480-99d6-11ea-9318-4867ac07c813.png)

![6](https://user-images.githubusercontent.com/22831002/82285511-99c38480-99d6-11ea-9d42-1a3108f72b8d.png)

iot sub 한곳에  가서 

```bash
C:\Users\student>mosquitto_sub -h 70.12.116.63 -t iot -p 1883
```



![5](https://user-images.githubusercontent.com/22831002/82285510-992aee00-99d6-11ea-8f67-04c20c6e6d40.png)



이후

![3](https://user-images.githubusercontent.com/22831002/82285508-98925780-99d6-11ea-900b-b08172752ac8.png)

이렇게 보내면 



결과값이 보내지게 된다.

![4](https://user-images.githubusercontent.com/22831002/82285509-992aee00-99d6-11ea-98de-f04402f27d0e.png)

