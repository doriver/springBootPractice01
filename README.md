* form제출 + controller     
* Ajax통신 + controller  
## SpringBoot 예외처리 구현
* [src/main/java/com/example/demo/exception/exhandler](https://github.com/doriver/ThymeleafController/tree/master/src/main/java/com/example/demo/exception/exhandler
) 에 @ControllerAdvice + @ExceptionHandler 로 api의 예외처리가 커스텀 되있음
* [src/main/java/com/example/demo/exception/api](https://github.com/doriver/ThymeleafController/tree/master/src/main/java/com/example/demo/exception/api)
  * ApiExceptionController.java : BasicErrorController에 의해 처리되는 api들
  * ApiExceptionV2Controller.java : 위 exhandler페키지에서 구현한것에 적용 받음
 
## BeanValidation
* @Controller에서 BeanValidation오류처리
  * 매개변수( @Validated, @ModelAttribute, BindingResult ) + 데이터객체( @NotBlank, @NotNull, @Range ... ) + 타임리프( th:object="", th:field="", th:errors="" )
  * ValidationItemController.java
* API에서( @RestController + @RequestBody ) BeanValidation
  * 매개변수( @Validated, @RequestBody, BindingResult ) + 데이터객체( @NotBlank, @NotNull, @Range ... )
  * ValidationItemApiController.java
