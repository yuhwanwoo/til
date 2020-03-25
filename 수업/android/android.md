## 안드로이드



### 설치

![image-20200323091819237](C:\Users\student\AppData\Roaming\Typora\typora-user-images\image-20200323091819237.png)





다운 파일을 c:\iot\setup으로 옮기자 ( 영어 경로로 해야하기때문 )





가상으로 테스트할수 있는 도구(AVD라고 지칭할 예정) default값으로 계속간다.

![image-20200324102325597](images/image-20200324102325597.png)



설치 끝나면

![image-20200324102612633](images/image-20200324102612633.png)





![image-20200324102641038](images/image-20200324102641038.png)





![image-20200324102655967](images/image-20200324102655967.png)



![image-20200324102716714](images/image-20200324102716714.png)

<img src="images/image-20200324102805433.png" alt="image-20200324102805433" style="zoom:25%;" />





![image-20200324104619126](images/image-20200324104619126.png)







![image-20200324105016089](images/image-20200324105016089.png)









![image-20200324105312263](images/image-20200324105312263.png)



![image-20200324105646347](images/image-20200324105646347.png)



![image-20200324111606408](images/image-20200324111606408.png)





![image-20200324111659139](images/image-20200324111659139.png)



![image-20200324112019852](images/image-20200324112019852.png)



![image-20200324114150267](images/image-20200324114150267.png)





![image-20200324114203408](images/image-20200324114203408.png)



x누르고  확인해보면

![image-20200324114322012](images/image-20200324114322012.png)

nexus(설정한 디바이스)가 있다.



확인한다.

![image-20200324114525626](images/image-20200324114525626.png)







### activity_main.xml

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout  //외부라이브러리 우리가 할때는 바꿀거야  xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent" 
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```





이렇게 바꾼다.

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout  								//수정한것 (우리가 흔히 만드는 비지니스앱은 Linear로도 충분하다)
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools" // 디바이스에는 전달 안되는 속성
    android:layout_width="match_parent"	// android : 실제 속성 등을 주는 접두사
    android:layout_height="match_parent"  // "match_parent" 폰사이즈에 꽉차게 채우겠다.
    tools:context=".MainActivity">

    <TextView			
        android:layout_width="wrap_content"  //"wrap_content"자기의 원래 사이즈 
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</LinearLayout>
```





```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"	//추가 시킨다.
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1" 	//일단 지운다
        android:text="Button" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1" //일단 지운다
        android:text="Button" />

    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1" //일단 지운다
        android:text="Button" />
</LinearLayout>
```











확인해보면 



![image-20200325094734697](images/image-20200325094734697.png)

그 후 버튼을 match_parent로 바꾸면

```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="match_parent"

        android:text="Button" />

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"

        android:text="Button" />

    <Button
        android:id="@+id/button3"
        android:layout_width="match_parent"
        android:layout_height="match_parent"

        android:text="Button" />
</LinearLayout>
```





![image-20200325094838172](images/image-20200325094838172.png)

버튼 세개가 합쳐져서 하나로 보인다





아래처럼 바꾼다.

![image-20200325094941043](images/image-20200325094941043.png)





### 코드 설명(위에 사진들)

layout_width : view의 너비

layout_height : view의 높이

orientation : 배치방향

id : 각 위젯을 식별할 수 있는 이름

​		btn

​		txt

margin : 주위여백

padding : 내부컨텐츠와 boarder사이의 간격

layout_weight : 여백을 해당 view의 사이즈로 포함

layout_gravity : parent내부에서 view의 정렬

gravity : view내부에서의 정렬



#### id 확인

![image-20200325101645485](images/image-20200325101645485.png)



![image-20200325101705988](images/image-20200325101705988.png)









![image-20200325101753280](images/image-20200325101753280.png)

200은 오류 뜨는데 단위가 없어서 그런다 dp를 사용한다.



#### l



### AndroidMainifest.xml 

```java
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="exam.day02.view.layout">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity"> // 액티비티에서 가장 먼저 실행될 액티비티부분
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />//
										// 위아래 두개 없으면 첫번째 실행하는거 아니야
                <category android:name="android.intent.category.LAUNCHER" />//
            </intent-filter>
        </activity>
    </application>

</manifest>
```

