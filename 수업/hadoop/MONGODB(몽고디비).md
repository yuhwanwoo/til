## MONGODB(몽고디비)

#### 다운받고 설치한다



![image-20200316094453502](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200316094453502.png)



사진에는 3.4.24버전이지만 3.6.17버전으로 한다

![image-20200316094418116](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200316094418116.png)





설치파일 실행 ( COMPLETE )

다운되면 그냥 끈다



#### 경로 복사해서 PATH설정

![image-20200316100547035](images/image-20200316100547035.png)

![image-20200316100831299](images/image-20200316100831299.png)



cmd에서 mongd

![image-20200316101118996](images/image-20200316101118996.png)



하지막 failure 떴는데  데이터(하둡으로 치면 하둡데이터)가 저장될 폴더가 필요

폴더를 만든다

![image-20200316101515926](images/image-20200316101515926.png)



그리고 명령어 실행할때 설정까지같이(원래 이렇게 안하고 기본으로 설정할수 있지만 우리는 안한다)

![image-20200316101615733](images/image-20200316101615733.png)



그러면 커서만 깜빡거리는 화면(서버기 때문이야)

![image-20200316101713639](images/image-20200316101713639.png)



cmd창 추가로 하나 더 열고 그냥 mongo를 입력하면

![image-20200316101944271](images/image-20200316101944271.png)



![image-20200316102306669](images/image-20200316102306669.png)

일단 뭐가 실행됐다고만 알아라



또 cmd로 mongo 입력하면 또 뜨는데 이는 다중접속이 된다는 것을 의미한다.

![image-20200316102419101](images/image-20200316102419101.png)



서버 띄운것을 확인하면 떴다는 것을 확인할 수 있다.

![image-20200316102430410](images/image-20200316102430410.png)



브라우저 실행하고 127.0.0.1:27017 실행하면( port 확인해서 숫자가 다를 수 있음 )

![image-20200316102604049](images/image-20200316102604049.png)



ctrl+C로 하나는 종료 시킨다

![image-20200316102704671](images/image-20200316102704671.png)



##### 기타설정은 아래 사이트 참조

https://docs.mongodb.com/v3.6/introduction/



### <<용어>>

collection : 테이블

document : 레코드

field : 컬럼

_id : 기본키



#### 다음 과정

use mydb => 내가 사용할 db로 접속

![image-20200316103526092](images/image-20200316103526092.png)

![image-20200316103547885](images/image-20200316103547885.png)

역시 아무 것도 안뜬다. 내 mydb안에 아무것도 안넣었기때문에 아무것도 안나오는것이다.

상태를 확인하면 그렇다

![image-20200316103927919](images/image-20200316103927919.png)



#### db에서 나온다

![image-20200316103958601](images/image-20200316103958601.png)





데이터베이스를 만들고(use mydb가 오라클의 conn) 그후 컬렉션을 만들고

(admin,config,local은 시스템 계정 같은거 인듯)



### 순서

#### 1. collection

1. collenction(rdbms에서 테이블)

   => 관계형 데이터베이스처럼 스키마를 정의하지 않는다.  (스키마 : 타입같은거)

   1) 종류

    * capped collection

      : 고정사이즈 주고 생성하는 컬렉션

      미리 지정한 저장공간이 모두 사용이 되면

      맨 처음에 저장된 데이터가 삭제되고 공간으로 활용

    *  non capped collection

      : 일반적인 컬렉션

   2) collection관리

   ​	[생성]

   ​	db.createCollection("컬렉션명")    => 일반 collection

   ​	db.createCollection("컬렉션명",{옵션list})    => 각각의 옵션을 설정해서 작업(json)

   ​	[삭제]

   ​	db.collection명.drop()

   ​	[컬렉션명 변경]

   ​	db.컬렉션명.renameCollection("변경할컬렉션명");

   

   

   아래에 보면 실습했다

   

   <img src="images/image-20200316110443870.png" alt="image-20200316110443870" style="zoom:80%;" />

   

![image-20200316104437962](images/image-20200316104437962.png)

​		여기서 test 테이블명

확인해보면 

![image-20200316104515513](images/image-20200316104515513.png)

![image-20200316104553444](images/image-20200316104553444.png)



![image-20200316111150568](images/image-20200316111150568.png)



collection생성

![image-20200316111417086](images/image-20200316111417086.png)



drop하는법

![image-20200316111827932](images/image-20200316111827932.png)



rename

![image-20200316111910052](images/image-20200316111910052.png)



capped여부도 명령어로 확인할 수 있다.

![image-20200316113642098](images/image-20200316113642098.png)



#### 2. mongodb의 insert

	##### 	[구문]

​	db.컬렉션명.insert({데이터...})

​	db.컬렉션명.insertOne({데이터...})

​	db.컬렉션명.insertMany({데이터...})

 - document(관계형 db에서 레코드 개념)에 대한 정보는 json의 형식으로 작성

 - mongdb에서 document를 삽입하면 자동으로

   _id가 생성  => 기본키의 역할

    "_id" : ObjectId("5e6ee790590a77a83c5c3b98")             => 밑 그림 참고

   ​								\-----------------------------------------

   ​							현재timestamp + machine Id + mongodb프로세스id + 순차번호

   

![image-20200316114425150](images/image-20200316114425150.png)

위에 보면 사이즈가 다른데 스키마가 없기때문에 들어간다(비정형 데이터에 적합!)





![image-20200316131258454](images/image-20200316131258454.png)

save와 insert의 차이는 나중에





자바스크립트기 때문에 명령어도 가능

![image-20200316131902908](images/image-20200316131902908.png)

![image-20200316131915200](images/image-20200316131915200.png)



##### [] 은 json의 배열 표기방식

![image-20200316132210512](images/image-20200316132210512.png)



##### 한번에 insert

![image-20200316132601211](images/image-20200316132601211.png)

여기서 확인해보면 _id(기본키)도 바꿔서 설정할 수 있다. 그러나 미리 있는 기본키로 insert하려고 하면 안된다

![image-20200316133158812](images/image-20200316133158812.png)



##### 과제

**1. mongo1day라는 이름으로 DB를 작성하세요**

**2. customer라는 이름으로 collection을 작성하세요**

**3. customer라는 collection에는 다음과 같은 성격의 필드를**

**갖고 저장될 수 있도록 임의의 데이터를 입력합니다.**

**==> document 5섯개**

**id : 기본 아이디**

**pass: 패스워드**

**name : 성명**

**info:{**

​     **city :거주했던 지역**

​      **[배열로 관리 - 기본 세 개 정도 입력하기]**

​      **toeicjumsu: 토익점수**

​      **[]로 다섯 개 정도 입력**

**}**

**city는 자유롭게 입력하시고 토익점수에 대한 배열은 다음과 같습니다.**

**[700,800,650,850,855]**

**[555,780,650,900,855]**

**[480,540,656,770,820]**

**[450,500,558,850,950]**

**[700,800,860,870,890]**

![image-20200316142659665](images/image-20200316142659665.png)







#### 3. mongodb에update

 * document수정
 * 조건을 적용해서 수정하기 위한 코드도 json으로 구현

[update를 위한 명령어]

$set: 해당필드의 값을 변경(업데이트를 하기 위한 명령어)

​			none capped collection인 경우 업데이트할 필드가 없는 경우 추가한다.

$inc:해당필의 저장된 숫자의 값을 증가

$unset : 원하는 필드를 삭제할 수 있다.

업데이트 옵션 : 

​			multi => true를 추가하지 않으면 조건에 만족하는 document중 첫 번째 document만 update



[구문]

db.컬렉션명.update({조건필드:값},	//sql의 update문 where절

​									 {$set:{수정할필드:수정값}},	//set절

​									 {update와 관련된 옵션:옵션값})







![image-20200316142858440](images/image-20200316142858440.png)

바뀐것 확인

![image-20200316142924941](images/image-20200316142924941.png)



![image-20200316143358241](images/image-20200316143358241.png)

3개를 넣었는데 1개만 변경된다( 맨 첫번째 ) 

그래서 옵션을 줘야한다.

![image-20200316143941931](images/image-20200316143941931.png)

이렇게 하면 kang의 val1이 모두 3000이 된다.

![image-20200316144011541](images/image-20200316144011541.png)



##### inc를 썼을때(증가)

​	

![image-20200316145017343](images/image-20200316145017343.png)



기존 3000에서 2000증가한 5000이 되었다

![image-20200316145111132](images/image-20200316145111132.png)



##### 컬렉션 따로 안만들어도 알아서 생성된다.

createCollection안했는데 score가 있음을 확인할수 있다.

![image-20200316144632560](images/image-20200316144632560.png)



##### $unset 하면 원하는 필드를 삭제할 수 있다. 

![image-20200316153352575](images/image-20200316153352575.png)



#### 4. mongodb에서 배열 관리

​	db.score.update({id:"jang"},{$set:{info:{city:["서울","안양"],movie:["겨울왕국2","극한직업","쉬리"]}}});

[배열에서 사용할 수 있는 명령어]

$addToSet

​	(없는 경우에만 값을 추가,중복을 체크)

​	db.score.update({id:"jang"},{$addToSet:{"info.city":"인천"}});

$push

배열의 요소를 추가

​	: 중복을 허용

​	db.score.update({id:"jang"},{$push:{"info.city":"천안"}})

$pop

​	배열에서 요소를 제거할 때 사용

​	1이면 마지막 요소를 제거,-1이면 첫 번째 요소를 제거

​	db.score.update({id:"jang"},{$pop:{"info.city":1}})

​	db.score.update({id:"jang"},{$pop:{"info.city":-1}})

$each : addToSet이나 push에서 사용할 수 있다.

​				여러 개를 배열에 추가할 때 사용

​	db.score.update({id:"jang"},{$push:{"info.city":{$each:["천안","가평","군산"]}}})

$sort : 정렬(1:오름차순,-1:내림차순)

db.score.update({id:"jang"},{$push:{"info.city":{$each:["천안","가평","군산"],$sort:1}}})



$pull : 배열에서 조건에 만족하는 요소를 제거(조건 한 개)

db.score.update({id:"jang"},{$pull:{"info.city":"천안"}})



$pullAll:배열에서 조건에 만족하는 요소를 제거(조건을 여러 개)

db.score.update({id:"jang"},{$pull:{"info.city":["가평","군산"]}})



![image-20200316155930274](images/image-20200316155930274.png)



중복의 유무

![image-20200316160043466](images/image-20200316160043466.png)



pop은 1을 넣으면 오른쪽 끝에서 지운다

![image-20200316161058313](images/image-20200316161058313.png)



-1을 넣으면 왼쪽 끝에서 지운다.

![image-20200316161346922](images/image-20200316161346922.png)



each는 여러개를 배열에 추가할때 쓴다.

![image-20200316161826553](images/image-20200316161826553.png)



pull 그냥 하면 안되는데

![image-20200316162814720](images/image-20200316162814720.png)



pullAll로 하면 지워진다.(pull은 조건하나 pullAll은 조건여러개)

![image-20200316162853859](images/image-20200316162853859.png)



db.score.find().pretty()를 쓰면 정리되어 볼 수 있다.

밑에 문제와 함께 캡쳐했음



------------------------------



##### 문제 

다음과 같은 조건으로 document를 구성해 보도록 하겠습니다.

게시물과 댓글을 mongodb에 저장

\1. board 컬렉션을 생성

\2. document는 5개 insert

no,id,title,content,count,writedate

\3. 2번째 게시물에는 댓글이 3개 추가되도록

update - 하위object와 배열로 구성

댓글의 필드

content,count1,count2,writedate

=> **코드와 실행결과 캡쳐**



db.board.insert({no:1,id:"multi1",title:"오늘은월요일"        ,content:"어렵다",count:1, writedate:new Date()})      db.board.insert({no:2,id:"multi2",title:"mongodb"         ,content:"배열에 대해서",count:1,writedate:new Date()})      db.board.insert({no:3,id:"multi3",title:"mongodb"        ,content:"find()",count:1,writedate:new Date()})      db.board.insert({no:4,id:"multi4",title:"mongodb"       ,content:"데이터구성하기",count:1, writedate:new Date()})      db.board.insert({no:5,id:"multi15",title:"내일은"      ,content:"화요일",count:1,writedate:new Date()})



![image-20200317094411941](images/image-20200317094411941.png)



------------------------------



#### 5. mongoDB에 저장된 데이터 조회하기 - find()





-------------

insert와 save의 차이

![image-20200317094803628](images/image-20200317094803628.png)

![image-20200317095128512](images/image-20200317095128512.png)



![image-20200317095240747](images/image-20200317095240747.png)



------------------------





count로 데이터의 개수를 알 수 있다.

![image-20200317095314055](images/image-20200317095314055.png)



while문을 통해서 var x 를 이용한 데이터 넣기

![image-20200317102133237](images/image-20200317102133237.png)



![image-20200317102234706](images/image-20200317102234706.png)



1) find

​	db.컬렉션명.find(조건,조회할 필드에 대한 명시)

 - db.컬렉션명.find({})와 동일

   :{}안에 아무것도 없으면 전체 데이터 조회

 - 조건,조회할 필드에 대한 명시 모두 json

 - 조회할 필드의 정보 명시

   형식:{필드명:1..} : 화면에 표시하고 싶은 필드

   ​		 {필드명:0} : 명시한 필드가 조회되지 않도록 처리

[조건]

$lt : <

$gt : >

$lte : <=

$gte : >=

$or : 여러 필드를 이용해서 같이 비교가능

$and : and 연산

$in : 하나의 필드에서만 비교

$nin :  non in		=>  in으로 정의한 조건을 제외한 documnent를 조회



\- addr이 인천인 데이터 : id, name, dept, addr

​	db.score.find({addr:"인천"},{id:1,name:1,dept:1,addr:1})

![image-20200317103604833](images/image-20200317103604833.png)



\- score컬렉션에서 java가 90점 이상인 document조회

​	id,name,dept,java만 출력

​	db.score.find({java: {$gte:90}},{id:1,name:1,dept:1,java:1,_id:0})

![image-20200317103943146](images/image-20200317103943146.png)



\- dept가 인사이거나 addr이 인천인 데이터 조회

​	db.score.find({$or:[{dept:"인사"},{addr:"인천"}]})

​		(조건이 여러개기 때문에 배열로했다)

![image-20200317104558225](images/image-20200317104558225.png)





\- id가 song이거나,kang이거나 hong인 데이터 조회

​	db.score.find({$or:[{id:"song"},{id:"hong"},{id:"kang"}]})

db.score.find({id:{$in:["song","hong","kang"]}})도 같은 결과가 나온다!

![image-20200317104857321](images/image-20200317104857321.png)



\- id가 song,kang, hong이 아닌 데이터 조회

![image-20200317105543113](images/image-20200317105543113.png)



null도 있다 

![image-20200317111145824](images/image-20200317111145824.png)



2) 조회메소드

 - findOne() : 첫 번째 document만 리턴

 - find() : 모든 document리턴

 - count() : 행의 개수를 리턴

 - sort({필드명:sort옵션}) : 정렬

   ​											1 => 오름차순

   ​											-1 => 내림차순

- limit(숫자) : 숫자만큼의 document만 출력

- skip(숫자) : 숫자만큼의 document를 skip하고 조회





sort

db.score.find().sort({id:1});

![image-20200317111631180](images/image-20200317111631180.png)



limit

![image-20200317111737708](images/image-20200317111737708.png)

위의 5개 document만





skip

![image-20200317111812639](images/image-20200317111812639.png)

위 5개 document 제외하고





3) 정규표현식을 적용

​	db.컬렉션명.find({조건필드명:/정규표현식/옵션})



#### [기호]

| : or

^ : ^뒤의 문자로 시작하는지 체크

i 옵션을 주면 대소문자 구분없이 조회가능

\[ ]: 영문자 하나는 한 글자를 희미하고 []로 묶으면 여러 글자를 표현

​				[a-i] a에서 i까지의 모든 영문자 





[옵션]

\- id가 kim과 park인 document조회

db.score.find({id:/kim|park/})

![image-20200317112457193](images/image-20200317112457193.png)

그러나 이건 대소문자를 구분하기때문에 KIM은 안나왔다



i옵션을 주면 대소문자 구분없이 조회가능

db.score.find({id:/kim|park/i})

![image-20200317112550036](images/image-20200317112550036.png)





\- id가 k로 시작하는 documnet조회

db.score.find({id:/^k/})

![image-20200317112802285](images/image-20200317112802285.png)

역시 i옵션이 먹힌다

![image-20200317112833094](images/image-20200317112833094.png)



\- [a-i]까지 영문이 있는 id를 조회 

db.score.find({id:/[a-i]/})



\- id가 k-p로 시작하는 document조회

db.score.find({id:/^[k-p]/})

![image-20200317113415774](images/image-20200317113415774.png)



\- id에서 k가 들어가거나 p가 들어가있는 document조회

db.score.find({id:/[kp]/}) 

![image-20200317113635450](images/image-20200317113635450.png)



not을 이용해서 null아닌거

![image-20200317141635591](images/image-20200317141635591.png)





servlet 값이 가장 작은 값 (null 제외시키기)

db.score.find({servlet:{$not:{$exists:null}}}).sort({servlet:1}).limit(1);

![image-20200317141722741](images/image-20200317141722741.png)



#### 6. mongoDB에 저장된 데이터 삭제하기 - remove()

 - 조건을 정의하는 방법은 find()나 update()와 동일

   db.score.remove({servlet:{$lt:80}});

![image-20200317114100465](images/image-20200317114100465.png)



#### 7. Aggregation

 - group by와 동일 개념

 - 간단한 집계를 구하는 경우 mapreduce를 적용하는 것보다 간단하게 작업

 - Pipeline을 내부에서 구현

   한 연산의 결과가 또 다른 연산의 input데이터로 활용

   https://docs.mongodb.com/v3.6/aggregation/#aggregation-pipeline 의 그림 참고

#### 1) 명령어(RDBMS와 비교)

​	$match : where절, having절

​	$group : group by

​	$sort : order by

​	$avg : avg그룹함수

​	$sum : sum그룹함수

​	$max : max그룹합수



##### [형식]

db.컬렉션명.aggregate(aggregate명령어를 정의)

​										\--------------------------------------

​										여러가지를 적용해야 하는 경우

​										배열

​		$group:{_id:그룹으로 표시할 필드명,

​						연산결과를 저장할 필드명:{연산함수:값}}

​																					   \---->숫자나 필드참조

​		$match:{필드명:{연산자:조건값}}

​										\------------------->

​	

\- addr별 인원수

​	db.exam.aggregate([

​										{$group:{_id:"$addr"

​												,num:{$sum:1}}

}

])

db.exam.aggregate([{$group:{_id:"$addr",num:{$sum:1}}}])



\- dept별 인원수

db.exam.aggregate([{$group:{_id:"$dept",num:{$sum:1}}}])

![image-20200317151033355](images/image-20200317151033355.png)

\- dept별 java점수의 평균

db.exam.aggregate([{$group:{_id:"$dept",avg:{$avg:"$java"}}}])

![image-20200317151018106](images/image-20200317151018106.png)

\- addr별 servlet합계

db.exam.aggregate([{$group:{_id:"$addr",서블릿합계:{$sum:"$servlet"}}}]);

![image-20200317151753295](images/image-20200317151753295.png)



\- dept별 java점수의 평균 단, addr이 인천인 데이터만 작업

​	$match를 추가

db.exam.aggregate([{$match:{addr:"인천"}},{$group:{_id:"$dept",평균:{$avg:"$java"}}}])

![image-20200317152505519](images/image-20200317152505519.png)





1. dept가 인사인 document의 servlet평균 구하기

   db.exam.aggregate([{$match:{dept:"인사"}},{$group:{_id:"$dept",평균:{$sum:1}}}])

2. java가 80점이 넘는 사람들의 부서별로 몇 명인지 구하기

   db.exam.aggregate([{$match:{java:{$gte:80}}},{$group:{_id:"$dept",합계:{$sum:1}}}]))

   ![image-20200317153920419](images/image-20200317153920419.png)

3. 2번 결과를 인원수데이터를 내림차순으로 정렬해 보세요.

   





---



### 부가

페이징과  솔트를 동시에 해주는 유용한기능

![image-20200317163753701](images/image-20200317163753701.png)

출처 : https://docs.spring.io/spring-data/mongodb/docs/2.2.5.RELEASE/reference/html/#repositories



![image-20200317163331017](images/image-20200317163331017.png)



자바 프로젝트 생성 후

dependency추가하기

![image-20200317163940073](images/image-20200317163940073.png)





![image-20200317171021831](images/image-20200317171021831.png)











------



### mongodb에 있는 데이터 가져오기(export)

![image-20200318092948597](images/image-20200318092948597.png)



iot에 생긴다

![image-20200318093102226](images/image-20200318093102226.png)



iot 디렉토리로 설정 되어있기때문에 마지막 o 옵션을 수정하면 저장된 곳을 바꿀수있다.

![image-20200318093649614](images/image-20200318093649614.png)





### import

기존의 test.csv 파일을 mongdb에 import하기

![image-20200318093721721](images/image-20200318093721721.png)





이렇게 입력하면 type 오류가 난다 --help를 입력하면 /로 수정해야함을 알 수 있다.

![image-20200318093817730](images/image-20200318093817730.png)





![image-20200318093913450](images/image-20200318093913450.png)

여기서 d,c는 데이터베이스명과 컬렉션명이고 type은 타입과 기술명 마지막에 /headline은 json파일의 맨위 필드명을 지우는것이다



확인해보면 데이터가 들어와 있음을 확인할 수 있다.

![image-20200318094120897](images/image-20200318094120897.png)