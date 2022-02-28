package com.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
public abstract class SpringMockMvcTestSupport extends SpringTestSupport {

	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
}
