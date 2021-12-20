package com.like.system.core.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.like.system.core.properties.KakaoProperties;

import reactor.core.publisher.Mono;

// https://blog.naver.com/stylehosting/222351870610
@RestController
@RequestMapping("/kakao")
public class KakaoController {

	private final KakaoProperties kakaoProperties;
	
	public KakaoController(KakaoProperties kakaoProperties) {
		this.kakaoProperties = kakaoProperties;
	}
	
	@GetMapping("/search")
	public String search(@RequestParam String query) {
		
		Mono<String> mono =  WebClient.builder().baseUrl("https://dapi.kakao.com")
		.build().get()
		.uri(builder -> builder.path("/v2/local/search/address.json")
				.queryParam("query", query)
				.build()
				)
		.header("Authorization", "KakaoAK " + kakaoProperties.getRestapi())
		.exchangeToMono(response -> {
			return response.bodyToMono(String.class);
		});
		
		return mono.block();
	}
}
