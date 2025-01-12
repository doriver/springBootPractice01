## 쿠키
* HttpServletResponse에 addCookie로 직접 응답에 쿠키 보내기    
* @CookieValue로 요청안에 있는 쿠키 조회

## 세션
* HttpServletRequest에서 getSession해서 세션ID에 해당하는 세션 얻거나, 새로운 세션 생성    
* 세션ID는 쿠키를 통해 전달되고, 클라이언트와 서버는 결국 쿠키로 연결됨

### 세션,쿠키로 간단한 로그인기능 구현
