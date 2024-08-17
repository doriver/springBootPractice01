package com.example.demo.validation.form.item;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemUpdateForm {

	@NotNull
	private Long id;

	@NotBlank
	private String itemName;
	
	@NotNull
	@Range(min = 1000, max = 100000)
	private Integer price;
	
	private Integer quantity;
}
