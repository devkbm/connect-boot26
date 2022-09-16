package com.like.system.login.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.like.SpringMockMvcTestSupport;
import com.like.system.login.boundary.LoginRequestDTO;
import com.like.system.login.domain.AuthenticationToken;
import com.like.system.login.service.LoginService;

class SystemLoginTest extends SpringMockMvcTestSupport {	

	@MockBean
	private LoginService service;
					
	@DisplayName("로그인 테스트")
	@Test
	void login() throws Exception {					
		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		AuthenticationToken testToken = AuthenticationToken.builder()
														   .userId("1")
														   .userName("test")
														   .build();
		
		given(this.service.login(new LoginRequestDTO("001", "1", "1234"), request)).willReturn(testToken);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); 
		params.add("username", "1"); 
		params.add("password", "1234");
						
		LoginRequestDTO param = LoginRequestDTO.builder()
											   .organizationCode("001")	
											   .staffNo("1")
											   .password("1234")
											   .build();
							
		
		//given(this.service.login(new LoginRequestDTO("1", "1234"), session))
		
		
		ResultActions result = this.mockMvc.perform(
				post("/api/system/user/login")					
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(param))
				
		);						
		
		result.andExpect(status().isOk())
		      .andDo(print())
		      .andDo(document("index"));
			  /*
		      .andDo(document("index", responseFields(
				fieldWithPath("userId").description("유저 아이디"),
				fieldWithPath("userName").description("비밀번호")
				)));	
			*/					
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	


}
