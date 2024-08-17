package com.example.demo.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.validation.form.item.ItemSaveForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

	@PostMapping("/add")
	public Object addItem( @RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
		log.info("API 컨트롤러 호출");
		
		if (bindingResult.hasErrors()) {
			log.info("검증 오류 발생 errors={}", bindingResult);
			return bindingResult.getAllErrors();
			// ObjectError 와 FieldError를 반환한다. 스프링이 이 객체를 JSON으로 변환해서 클라이언트에 전달
			// 실제 개발할 때는 이 객체들을 그대로 사용하지 말고, 필요한 데이터만 뽑아서 별도의 API 스펙을 정의하고 그에맞는 객체를 만들어서 반환
		}
		
		log.info("성공 로직 실행");
		return form;
	}
}
