package com.example.demo.member;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controller.MemberController;

@WebMvcTest(MemberController.class) // Controller를 구체적으로 적을 수 있고, ControllerAdvice, Filter 등을 포함과 제외시킬 수 있어 Security에 대한 Test도 가능
// Mvc테스트 위한거, 컨트롤러가 설계대로 동작하는지에 대해 검증하는데 필요
public class MemberControllerTest {

	@Autowired
	MockMvc mvc;
	// 실제로 서블릿 컨테이너를 사용하지 않고, 테스트용으로 Mvc 기능을 사용할 수 있게 해주는 역할
	// 테스트 때 생성되는 WebApplicationContext에서 주입받는다
	
	@MockBean
	MemberServiceImpl memberService;
	
	
	@Test
	@DisplayName("리스트 반환하기")
	void getList() throws Exception {
		// given
		List<MemberResponseDto.ListDto> lit 
			= List.of(new MemberResponseDto.ListDto("asb", 10), new MemberResponseDto.ListDto("fsd", 12));
		Mockito.when(memberService.findAll()).thenReturn(lit);
		
		// when, then
		mvc.perform(MockMvcRequestBuilders.get("/members").contentType(MediaType.APPLICATION_JSON)) // 컨트롤러에게 요청을 보내는 역할
			.andDo(MockMvcResultHandlers.print()) // 요청과 응답에 대한 것들을 콘솔에 출력
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("fsd"))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("asb"));
	}
	
}
