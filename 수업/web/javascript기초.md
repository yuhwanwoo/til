## javascript

#### 초급 

1. 문법
   * 사용방법
   * 변수
   * 제어구문
2. 함수
3. 내장객체
   * String, Array Date, Math

4. 이벤트핸들러
5. 브라우저객체모델(BOM)



#### 중급

1. DOM
2. JSON
3. Ajax
4. 자바스크립트 프로토타입
   * 사용자 정의 객체 정의
   * 클로저



#### 고급

자바스크립트의 프레임워크 사용

1. bootstrap - css와 javascript의 프레임워크
2. MEAN stack
   * MongoDB
   * expressJs(express.js)
   * Angularjs
   * NodeJs
3. ReactJs - 웹 UI개발에 사용(facebook이 공개한 오픈소스)
4. Vue.js
5. D3





### BOM

- 브라우저에 출력되는 모든 구성요소를 객체로 정의하고 접근
- window
- location
- document
- form(양식태그)
- image

1.  접근방법

   * 모든 객체는 계층구조를 갖고 있다.

     window.document.......

   * form태그와 form태그 하위 태그를 객체로 접근하기 위해서 name속성을 정의하고 접근

     \<form name="myform">
         아이디:<input type="text" name="id">
         패스워드:<input type="password" name="pass">
     </form>

     

     window.document.풀객체.텍스트객체

     window.document.myform.id.속성(메소드)

     

     주로 window.document는 생략

   * id를 정의하는 경우

     \<div id="mydiv">
         
     </div>

     ​	객체 = documnet.getElementById("mydiv")

     

     

### window

 	1. 대화상자
     - alert
     - prompt
     - confirm
	2. popup
    * open
    * close
	3. 자동실행
    * setTimeout
    * setInterval
    * clearInterval
	4. 데이터처리
    * parseInt : 숫자모양을 한 문자열을 숫자로 변환
    * isNaN : 입력받은 값이 숫자인지 문자인지 확인( 타입을 비교하지 않고 실제 값을 비교 - 문자가 입력되면 true)
    * eval : 매개변수를 전달된 데이터(식, 연산)를 실제로 실행
    * trim : 공백을 제거

### event

	* onclick
	* onkeyup
	* onmouseover, onmouseout
	* onchange
	* onload