package com.example.demo.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@PostMapping("/add")	// @ModelAttribute   , @RequestBody
	public Object addItem( @Validated @RequestBody ItemSaveForm form, BindingResult bindingResult) {
		log.info("API 컨트롤러 호출");
		
		if (bindingResult.hasErrors()) {
//			List<ObjectError> loo = bindingResult.getAllErrors();

			Map<String, String> validationMessage = new HashMap<>();
			
			List<FieldError> foo = bindingResult.getFieldErrors();
			FieldError tmp = null;
			for (int i = 0; i < foo.size(); i++ ) {
				tmp = foo.get(i);
				validationMessage.put(tmp.getField(), tmp.getDefaultMessage());
									// price , 1000에서 100000 사이여야 합니다
			}
		
			return validationMessage;
			// ObjectError 와 FieldError를 반환한다. 스프링이 이 객체를 JSON으로 변환해서 클라이언트에 전달
			// 실제 개발할 때는 이 객체들을 그대로 사용하지 말고, 필요한 데이터만 뽑아서 별도의 API 스펙을 정의하고 그에맞는 객체를 만들어서 반환
		}
		
		log.info("성공 로직 실행");
		return form;
	}
}
