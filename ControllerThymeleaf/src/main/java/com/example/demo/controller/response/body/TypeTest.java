package com.example.demo.controller.response.body;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.validation.domain.item.Item;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/typeTest")
public class TypeTest {
	
	@GetMapping("/string")
	public String ss() {
		return "asdjflajdfl";
	} // 응답Content-Type : text/plain;charset=UTF-8

	@GetMapping("/mapss")
	public Map<String, String> mm() {
		Map<String, String> m = new HashMap<>();
		m.put("aa", "bb");
		m.put("11", "cc");
		m.put("dd", "22");
		return m;
	} // 응답Content-Type : application/json

	@GetMapping("/list")
	public List<Object> li() {
		List<Object> ll = new LinkedList();
		ll.add(11);
		ll.add("aa");
		ll.add("22");
		return ll;
	} // 응답Content-Type : application/json

	@GetMapping("/instance")
	public Item mm2() {
		return new Item("이름",1,2);
	} // 응답Content-Type : application/json

	
	
	
	

}
