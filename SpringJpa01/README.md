## JDBC로만 DB작업해보기
[src/test/java/com/example/demo/jdbc](https://github.com/doriver/SpringJpa01/tree/master/src/test/java/com/example/demo/jdbc)     
DriverManager > Connection > PreparedStatement     
MySQL드라이버가 JDBC인터페이스를 구현해서 실질적으로 DB와 작업( DB와 연결, SQL전달, 결과 받기 등 )함

## SpringJPA로 CRUD
* [~ crud/basic](https://github.com/doriver/SpringJpa01/tree/master/src/main/java/com/example/demo/crud/basic) 에 기본crud

## 테스트 코드
* [src/test/java/com/example/demo/member](https://github.com/doriver/SpringJpa01/tree/master/src/test/java/com/example/demo/member) 에 단위테스트, 통합테스트 연습
* 단위테스트의 경우 Controller, Service, Repository, Entity 계층에대해 각각 구현 연습
  * Mockito를 활용해 가짜 객체에 원하는 결과를 Stub하여 단위 테스트를 진행  
     
@DataJpaTest 로 JPA테스트 , Querydsl 사용
