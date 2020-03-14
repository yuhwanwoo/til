### flume

### : 데이터를 추출하기 위해 사용되는 프로그램

### ===>> 시스템로그, 웹서버의 로그, 클릭로그, 로안로그... 비정형데이터를 HDFS에 적재하기 위해 사용하는 프로그램

### 대규모의 로그데이터가 발생하면 효율적으로 수집하고 저장하기 위해 관리

### ==> flume, chukwa, scribe, fluentd,splunk



#### - 설치

![image-20200313102916819](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200313102916819.png)



![image-20200313102937166](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200313102937166.png)



![image-20200313103033685](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200313103033685.png)





![image-20200313103704456](images/image-20200313103704456.png)

#### wget 명령어로 다운(주소 붙여넣기)

wget http://archive.apache.org/dist/flume/1.6.0/apache-flume-1.6.0-bin.tar.gz

![image-20200313103744650](images/image-20200313103744650.png)





#### 압축풀기

tar -zxvf apache-flume-1.6.0-bin.tar.gz

![image-20200313103941396](images/image-20200313103941396.png)



#### 파일 설정하기

/home/hadoop/.bashrc  에 설정한다

export FLUME_HOME=/home/hadoop/apache-flume-1.6.0-bin

export PATH=$PATH:$FLUME_HOME/bin

(나머지는 하둡 관련임)

![image-20200313104336931](images/image-20200313104336931.png)



#### source를 통해 .bashrc 를 실행

![image-20200313104917871](images/image-20200313104917871.png)





#### template 파일은 이렇게 설정파일을 설정하라는 예시 이므로 cp를 통해 sh파일로 복사한다.(flume-env.sh rename하고 정보등록)

![image-20200313105258258](images/image-20200313105258258.png)



![image-20200313105342117](images/image-20200313105342117.png)

![image-20200313111100079](images/image-20200313111100079.png)



#### 파일을 실행하여 설정해야한다. jdk 홈디렉토리와 jadoop 홈디렉토리를 export로 설정

이때 jdk는 미리 깔아져있는상태여야함

![image-20200313111600385](images/image-20200313111600385.png)



그리고 설정

![image-20200313111409371](images/image-20200313111409371.png)





#### flume 설정파일에 등록

-flume-conf.properties.template을 rename해서 XXXX.properties

  * cp flume-conf.properties.template console.properties

![image-20200313111925321](images/image-20200313111925321.png)



-flume agent의 source,channel,sink에 대한 정보를 등록

* (우리는 기존내용을 다 지우고 했다)

  ![image-20200313114340862](images/image-20200313114340862.png)

  ![image-20200313133218153](images/image-20200313133218153.png)

  

[Flume의 구성요소]

flume의 실행 중인 프로세스를 agent라 부르며 source,channel,sink로 구성

![image-20200313112032206](images/image-20200313112032206.png)

​										(사진 참조: flum 홈페이지)

1. source

   => 데이터가 유입되는 지정(어떤 방식으로 데이터가 유입되는지 type으로 명시)

   agent명.sources.source명.type=값

   1) type

   - netcat : telnet을 통해서 터미널로 들어오는 입력데이터(bind:접속IP, port:접속할 port)

   - spoolDir : 특정 폴더에 저장된 파일(spoolDir : 폴더명)

     ![image-20200313144352085](images/image-20200313144352085.png)

2. channel

   => 데이터를 보관하는 곳(source와 sink사이의 Queue)

3. sink

   =>데이터를 내보내는 곳(어떤 방식으로 내보낼지 정의)

   1) type

   * logger : flume서버 콘솔에 출력이 전달

     flume을 실행할 때  -Dflume.root.logger=INFO,console 를 추가

   * file_roll : file을 읽어서 가져오는 경우(directory : 읽어온 파일을 저장할 output폴더를 명시)



#### [flume의 실행]

![image-20200313115450564](images/image-20200313115450564.png)

![image-20200313115526834](images/image-20200313115526834.png)



 ./bin/flume-ng agent 

--conf conf --conf-file ./conf/console.properties

--name myConsole -Dflume.root.logger=INFO,console

​									\--------------------------

​										source가 talnet으로 입력하는 데이터인 경우



실행 명령어: ./bin/flume-ng agent

옵션

	* --conf : 설정파일이 저장된 폴더명(-c)
	* --conf-file : 설정파일명(-f)
	* --name : agent의 이름(-n)
	* -Dflume.root.logger=INFO,console : flume의 로그창에 기록

![image-20200313141627381](images/image-20200313141627381.png)

 이런 화면에서 멈추게 된다 

서버를 실행한거기 때문에 이렇게 멈추는게 맞다

### telnet 다운(?)

새 터미널을 띄우고

![image-20200313135149558](images/image-20200313135149558.png)



#### telnet을 이용해서 실행(?)

![image-20200313141551030](images/image-20200313141551030.png)

여기서 test라고 입력하게 되면

![image-20200313141733944](images/image-20200313141733944.png)

기본 터미널을 확인해보면

![image-20200313141815349](images/image-20200313141815349.png)



(telnet에서 나오려면 ctrl+] 누르고 엔터 후 quit 입력하면 된다)

![image-20200313143414412](images/image-20200313143414412.png)



#### 플룸 종료

ctrl+C 로 종료



#### 그 후 console.properties rename 한 후 폴더 두개 생성

./conf/console.properties ./conf/myfolder.properties

cd ..

mkdir input

mkdir output

![image-20200313143806000](images/image-20200313143806000.png)



#### myfolder.properties에 들어가서 파일 수정

![image-20200313150827044](images/image-20200313150827044.png)

#### flume 실행

./bin/flume-ng agent -c conf -f ./conf/myfolder.properties -n myConsole

![image-20200313161628115](images/image-20200313161628115.png)



![image-20200313161743534](images/image-20200313161743534.png)



그리고 파일을 input에 넣으면

![image-20200313161814509](images/image-20200313161814509.png)

output폴더에 로그가 남는다.



#### console.properties rename하고 파일 수정 (hdfs)

![image-20200313155153162](images/image-20200313155153162.png)

![image-20200313160608609](images/image-20200313160608609.png)

#### flume 실행

./bin/flume-ng agent -c ./conf/ -f ./conf/hdfs.properties -n myhdfs

![image-20200313161418268](images/image-20200313161418268.png)



그후 inputdata나 json파일을 input폴더에 넣으면

![image-20200313161930023](images/image-20200313161930023.png)

hadoop 확인하면

![image-20200313162023440](images/image-20200313162023440.png)

![image-20200313162037516](images/image-20200313162037516.png)







#### hdfs2.properties를 기존 1파일을 다른이름으로 저장해서 등록하고 내용 작성



![image-20200314094811053](images/image-20200314094811053.png)

![image-20200314102954527](images/image-20200314102954527.png)

​		(플룸홈페이지 참조)

![image-20200314103024035](images/image-20200314103024035.png)

![image-20200314103043586](images/image-20200314103043586.png)

![image-20200314103853260](images/image-20200314103853260.png)





#### hdfs3.properties를 기존 2파일을 다른이름으로 저장해서 등록하고 내용 작성

![image-20200314104831602](images/image-20200314104831602.png)

14번줄은 for문 이용해서 cat 명령어 쓴다는 뜻인듯( 플룸 홈페이지에 설명 있음)



![image-20200314103758450](images/image-20200314103758450.png)

![image-20200314105340889](images/image-20200314105340889.png)



#### hadoop02를 was로 하고 톰캣 설치

![image-20200314105426323](images/image-20200314105426323.png)

![image-20200314105442087](images/image-20200314105442087.png)



그후 명령어로 압축을 푼다(tar -zxvf (압축파일명))



#### hadoop01의 .hashrc 파일을 hadoop02로 복사

scp .bashrc hadoop@hadoop02:/home/hadoop/.bashrc

![image-20200314111547476](images/image-20200314111547476.png)



파일을 실행하여 어떤건 주석처리하고 설정을 입력한다.

![image-20200314112641187](images/image-20200314112641187.png)



그후 적용을 위해 source 명령어

![image-20200314112730594](images/image-20200314112730594.png)



#### 톰캣 실행해서

![image-20200314113153927](images/image-20200314113153927.png)



cf) 톰캣이 실행되지 않은 채로 netstat하면

![image-20200314113313154](images/image-20200314113313154.png)

time_wait가 된다





다시 돌아와서 firefox에 127.0.0.1:8080 을 입력하면

![image-20200314113428169](images/image-20200314113428169.png)



이 화면이 나오면 제대로 된 것이다



#### 계정 설정 

![image-20200314114813077](images/image-20200314114813077.png)





### 저장 후 firefox 창에 127.0.0.1:8080/manager 입력하고 admin/admin 입력

![image-20200314115213950](images/image-20200314115213950.png)

이 화면이 나온다.









![image-20200314132426083](images/image-20200314132426083.png)





그냥 윈도우에서 크롬을 실행하고  아래 주소를 입력하면 화면이 떠야 하지만 안뜬다 

![image-20200314132826223](images/image-20200314132826223.png)



톰캣  아래 주소의 파일로 들어가서 주석하면

![image-20200314133002690](images/image-20200314133002690.png)



가능해 진다.

![image-20200314133052821](images/image-20200314133052821.png)

![image-20200314133135041](images/image-20200314133135041.png)



그 후 나중에 게스트로 들어오게 하려면



밑을

![image-20200314133508027](images/image-20200314133508027.png)



이렇게 자신의 아이피로 수정하면(cmd-ipconfig)

![image-20200314133611356](images/image-20200314133611356.png)

(나중에 집에서도 접속 가능한듯)



![image-20200314134742991](images/image-20200314134742991.png)



![image-20200314134827114](images/image-20200314134827114.png)





![image-20200314134510826](images/image-20200314134510826.png)



그리고 배치를 누른다음 배치가 되면

주소 입력시에 제대로 접속된다. (확실하진 않지만 기존 톰캣을 스프링에서 했다면 지금은 리눅스에서 했다는 뜻인듯)

![image-20200314134947663](images/image-20200314134947663.png)





## 여기에서 퀴즈

1. 3번에 WAS를 구축

2. WAS에 bigdataShop을 배포

3. 3번에 flume을 설치

4. tomcat의 access log를 hdfs에 저장

   * arvo통신

   * hdfs

     /flume/tomcatelog

5. 메일로 제출

   * 3번의 was manager화면에 배포된 목록 캡처
   * hdfs에 저장된 access log캡쳐
   * 각 머신의 flume 설정파일