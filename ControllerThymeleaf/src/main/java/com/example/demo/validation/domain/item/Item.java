package com.example.demo.validation.domain.item;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Item {

	private Long id;

	@NotBlank
	private String itemName;
	
	@NotNull
	@Range(min = 1000, max = 100000)
	private Integer price;
	
	@NotNull
	@Max(9999)
	private Integer quantity;
	
	public Item() {
		
	}
	
	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
