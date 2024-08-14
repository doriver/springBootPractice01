* form제출 + controller     
* Ajax통신 + controller  
## SpringBoot 예외처리 구현
* [src/main/java/com/example/demo/exception/exhandler](https://github.com/doriver/ThymeleafController/tree/master/src/main/java/com/example/demo/exception/exhandler
) 에 @ControllerAdvice + @ExceptionHandler 로 api의 예외처리가 커스텀 되있음
* [src/main/java/com/example/demo/exception/api](https://github.com/doriver/ThymeleafController/tree/master/src/main/java/com/example/demo/exception/api)
  * ApiExceptionController.java : BasicErrorController에 의해 처리되는 api들
  * ApiExceptionV2Controller.java : 위 exhandler페키지에서 구현한것에 적용 받음
