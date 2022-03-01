package com.like.system.user.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.like.SpringMockMvcTestSupport;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.service.UserService;

public class UserControllerTest extends SpringMockMvcTestSupport {

	@MockBean
	private UserService userService;
					
	@DisplayName("사용자 조회")
	@Test
	void getUser() throws Exception {
		SystemUser user = SystemUser.builder()
									.userId("test")
									.name("test")
									.build();
		userService.saveUser(user);			
		
		// when
		ResultActions result = this.mockMvc.perform(
				get("/api/common/user/test")					
					.contentType(MediaType.APPLICATION_JSON)					
				
		);
		
		result.andExpect(status().isOk())
	      .andDo(print())
	      .andDo(document("user"));
	}
}
