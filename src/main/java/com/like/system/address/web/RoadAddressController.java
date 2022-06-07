package com.like.system.address.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.system.address.boundary.RoadAddressApiRequest;

@RestController
public class RoadAddressController {

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
		String confmKey = "devU01TX0FVVEgyMDIyMDYwNzIyMjI1MzExMjY1ODY=";		// req.getParameter("confmKey");
		String keyword = "은계중앙로";		// req.getParameter("keyword");
		
		String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+currentPage
		  			  + "&countPerPage="+countPerPage+"&keyword="+URLEncoder.encode(keyword,"UTF-8")
					  + "&confmKey="+confmKey+"&resultType="+resultType;
		
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

}
