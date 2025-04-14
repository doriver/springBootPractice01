package com.example.demo.validation02;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestUrlEncodedDto;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/valid")
public class UrlEncodedVallidController {
	
	@RequestMapping("/u1")
	public String ex02(@ModelAttribute @Valid RequestUrlEncodedDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return validationStr(bindingResult);
		}
		log.info("{} {}", dto.getName(), dto.getAge());
		return "통과";
	}
	
	public String validationStr(BindingResult bindingResult) {
		StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
        	if (fieldError.isBindingFailure()) {
        		builder.append(fieldError.getField());
        		builder.append("(은)는 ");
        		builder.append(fieldError.getDefaultMessage());
        	} else {
        		builder.append(fieldError.getField());
        		builder.append("(은)는 ");
        		builder.append(fieldError.getDefaultMessage());
        		builder.append(", 입력된 값: ");
        		builder.append(fieldError.getRejectedValue());
        		builder.append("\n");        		
        	}
        }
        return builder.toString();
	}
}
