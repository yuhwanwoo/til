#### swing

​	=> java fx(css,javascript)



이클립스 플러그인 window builder툴을 설치하고 드래그앤 드랍으로 화면디자인



1. 서버 실행

   ChatServerView(ChatServerListener)

   -----------------\---

    			서버가 여러 가지 상태를 확인할 수 있는 화면

2. 클라이언트 접속

   1) ChatLogin(ChatLoginListener)을 먼저 실행해서 로그인(ip,port,채팅nickname)

   2) ClientChatView가 실행(ClientChatListener)

   ​	--------------\---------	

   ​			클라이언트가 채팅하는 화면	