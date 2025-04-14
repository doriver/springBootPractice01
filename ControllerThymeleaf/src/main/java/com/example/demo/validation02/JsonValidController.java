package com.example.demo.validation02;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestJsonDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/valid")
public class JsonValidController {

	@PostMapping("/t1")
	public String ex01(@RequestBody @Valid RequestJsonDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validationStr(bindingResult);
		}
		log.info("{} {}", dto.getName(), dto.getAge());
		return "통과";
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public String httpMessageNotReadableHandler(HttpMessageNotReadableException e) {
		log.error("[exceptionHandler] ex", e);
		
        Throwable cause = e.getCause(); // 원인 확인
        
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) cause;
            String field = invalidFormatException.getPath().get(0).getFieldName();
            
            return  field + "의 타입(숫자,문자 등등)이 잘못됐습니다.";
        } 
        return "asd";
	}
	
	
	public String validationStr(BindingResult bindingResult) {
		StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getField());
            builder.append("(은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(", 입력된 값: ");
            builder.append(fieldError.getRejectedValue());
            builder.append("\n");
        }
        return builder.toString();
	}
	
	@PostMapping("/t2")
	public String ex02(@RequestBody @Valid RequestJsonDto dto) {
		log.info("{} {}", dto.getName(), dto.getAge());
		return "통과";
	}
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
    	log.error("[exceptionHandlerJson]", ex);
        BindingResult bindingResult = ex.getBindingResult();
       
        return validationStr(bindingResult);
    }
}
