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







