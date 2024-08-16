package com.example.demo.validation;

import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BeanValidationTest {

	@Test
	void beanValidation() {
		// 검증기를 생성
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Item item = new Item();
		item.setItemName("   ");
		item.setPrice(0);
		item.setQuantity(1111111111);
		
		// 검증 실행
		Set<ConstraintViolation<Item>> violations = validator.validate(item);
		
		// 검증 결과 확인
		for (ConstraintViolation<Item> violation : violations) {
			System.out.println("violation : " + violation);
			System.out.println("violation.message : " + violation.getMessage());
		}
	}
}
