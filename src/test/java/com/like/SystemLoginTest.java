package com.like;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.like.system.login.boundary.LoginRequestDTO;

class SystemLoginTest extends SpringMockMvcTestSupport {
	
	@DisplayName("로그인 테스트")
	@Test
	void login() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); 
		params.add("username", "1"); 
		params.add("password", "1234");
		
		LoginRequestDTO param = LoginRequestDTO.builder()
											   .username("1")
											   .password("1234")
											   .build();
							
		ResultActions result = this.mockMvc.perform(
				post("/common/user/login")					
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(param))
				
		);				
		/*
		.andDo(document("index", responseFields(
				fieldWithPath("username").description("유저 아이디"),
				fieldWithPath("password").description("비밀번호")
				))
		*/		
		
		result.andExpect(status().isOk())
			  .andDo(document("index", responseFields(
				fieldWithPath("userId").description("유저 아이디"),
				fieldWithPath("userName").description("비밀번호")
				)));	
		
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	


}
