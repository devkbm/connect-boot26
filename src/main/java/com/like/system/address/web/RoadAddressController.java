package com.like.system.address.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.like.system.address.boundary.RoadAddressApiRequest;
import com.like.system.address.boundary.RoadAddressApiResult;
import com.like.system.address.config.RoadAddressProperties;

import reactor.core.publisher.Mono;

@RestController
public class RoadAddressController {
	
	RoadAddressProperties property;
	WebClient client;	
	
	public RoadAddressController(RoadAddressProperties property) {
		this.property = property;
		
		client = createWebClient();
	}	
			
	@GetMapping("/api/address")
	private Mono<ResponseEntity<RoadAddressApiResult>> getRoadArddressJson(@Valid RoadAddressApiRequest dto) throws Exception {
					
		int currentPage = Objects.isNull(dto.currentPage()) ? 1 : dto.currentPage(); 	
		int countPerPage = Objects.isNull(dto.countPerPage()) ? 10 : dto.countPerPage(); 				
						
		return client.get()				     
				  	 .uri(uriBuilder -> uriBuilder
				  			.queryParam("confmKey", property.getConfmKey())
				  			.queryParam("keyword", dto.keyword())
				  			.queryParam("currentPage", currentPage)
				  			.queryParam("countPerPage", countPerPage)				  							  			
				  			.queryParam("resultType", "json")
				  			.build()).accept(MediaType.APPLICATION_JSON)
		        	 .retrieve()
		        	 .toEntity(RoadAddressApiResult.class);
	}	
	
	// confmKey - devU01TX0FVVEgyMDIyMDYwNzIyMjI1MzExMjY1ODY=
	//@RequestMapping(value="/sample/getAddrApi.do")
	@GetMapping("/sample/getAddrApi.do")
	public void  getAddrApi(RoadAddressApiRequest searchVO
			 			   ,HttpServletRequest req
			 			   ,ModelMap model
			 			   ,HttpServletResponse response) throws Exception {

		String currentPage = "1"; 	// req.getParameter("currentPage");
		String countPerPage = "10"; // req.getParameter("countPerPage");
		String resultType = "json";	// req.getParameter("resultType");
		String confmKey = property.getConfmKey(); // "devU01TX0FVVEgyMDIyMDYwNzIyMjI1MzExMjY1ODY=";		// req.getParameter("confmKey");
		String keyword = "은계중앙로";		// req.getParameter("keyword");
		
		String apiUrl = property.getApiUrl()+"?currentPage="+currentPage
		  			  + "&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")
					  + "&confmKey="+confmKey+"&resultType="+resultType;
		/*
		String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage
	  			  + "&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")
				  + "&confmKey="+confmKey+"&resultType="+resultType;
		*/
		
	   	URL url = new URL(apiUrl);
	   	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
	   	StringBuffer sb = new StringBuffer();
	   	String tempStr = null;
	   	
	   	while(true) { 
	    	tempStr = br.readLine();
	    	if(tempStr == null) break;
	    	sb.append(tempStr);	
    	}
	   	
    	br.close();
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/juso_support_center/json");
    	response.getWriter().write(sb.toString());
    }

	private WebClient createWebClient() {
		return WebClient.builder()
						.baseUrl(property.getApiUrl())						
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build();
	}
	
}