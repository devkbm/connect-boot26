package com.like.system.core.security.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.like.system.core.oauth.CustomOAuth2UserService;
import com.like.system.core.security.CustomCsrfFilter;
import com.like.system.core.security.RestAuthenticationEntryPoint;
import com.like.system.core.security.RestLoginFailureHandler;
import com.like.system.core.security.RestLoginSuccessHandler;
import com.like.system.user.service.SpringSecurityUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 로컬 테스트용 Spring Secury 설정
 * CSRF 설정 제거
 *
 */
@Slf4j
@Configuration
@EnableWebSecurity
@Profile("localtest")
public class WebSecurityConfigLocalTest extends WebSecurityConfigurerAdapter {

	@Autowired
	SpringSecurityUserService userService;
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	/*
	 * 인증되지 않은 접근에 대해 redirect(302)시키지 않고 401 Status 리턴
	 */
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		
	/**
	 * 로그인 성공후 default target page로 redirect(302)하지 않고 Http Status(200) 리턴
	 */
	@Autowired
	private RestLoginSuccessHandler authSuccessHandler;
	
	/**
	 * 로그인 실패 시 redirect(302)하지 않고 Http Status(401) 리턴
	 */
	@Autowired
	private RestLoginFailureHandler authFailureHandler;
	
	private static final String[] CSRF_IGNORE = {"/common/user/login","/static/**","/h2-console/**"};
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.csrf().disable()							
			.cors().configurationSource(corsConfigurationSource()).and()
			.headers().frameOptions().disable().and()	// h2-console 테스트를 위한 설정
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()			
			.authorizeRequests()
			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/common/user/login").permitAll()								
				.antMatchers("/oauth/user").permitAll()
				.antMatchers("/oauth2/authorization/**").permitAll()				
				.antMatchers("/ex").permitAll()
				
				//.antMatchers("/common/menuhierarchy/**").permitAll()
				//.antMatchers("/grw/**").permitAll()//hasRole("USER")							
				.anyRequest().authenticated().and()		// 인증된 요청만 허용
				//.anyRequest().permitAll().and()				// 모든 요청 허용(테스트용도)
			// 모든 연결을 HTTPS로 강제 전환
			//.requiresChannel().anyRequest().requiresSecure().and()
			
			.formLogin()				
				.loginProcessingUrl("/login")				
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(authSuccessHandler)
				.failureHandler(authFailureHandler)
				.permitAll().and()			
			.logout()
				.logoutUrl("/common/user/logout")
				.logoutSuccessHandler(this::logoutSuccessHandler)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll().and()
			.oauth2Login()
				.defaultSuccessUrl("/loginSuccess")							
				.authorizationEndpoint()
					.authorizationRequestRepository(authorizationRequestRepository()).and()
				.tokenEndpoint()
					.accessTokenResponseClient(accessTokenResponseClient()).and()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService);
			//http.portMapper().http(8080).mapsTo(8443);
		//http.addFilterBefore(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);	
		
	}
	
	@Bean
	public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
		return new NimbusAuthorizationCodeTokenResponseClient();
	}

	@Bean
	public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {		
		return new HttpSessionOAuth2AuthorizationRequestRepository();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
       CorsConfiguration configuration = new CorsConfiguration();              
       configuration.setAllowedOrigins(Arrays.asList("http://localhost:8090","http://localhost:4200","http://kbm0417.gonetis.com:4200","http://kbm0417.gonetis.com"));
       //configuration.setAllowedOrigins(Arrays.asList("*"));
       configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
       //configuration.setAllowedMethods(Arrays.asList("*"));
       
       // Request Header에 Http default 이외에 정해진 것만 허용한다.
       configuration.setAllowedHeaders(Arrays.asList("Origin", "Accept", "X-Requested-With", "Content-Type", "remember-me", "x-auth-token", "Authorization", "x-xsrf-token", "XSRF-TOKEN", "X-Access-Token","Access-Control-Request-Method","Access-Control-Request-Headers"));
       //configuration.setAllowedHeaders(Arrays.asList("*"));
       
       //configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));       
       
       // browser에서 Access-Control-Allow-Credentials: true가 없으면 거절한다. 즉, xmlhttprequest header에 쿠키가 있어야 한다.
       configuration.setAllowCredentials(true);
       
       // preflight가 전송된 후 60분 이후 만료된다.
       configuration.setMaxAge(3600L);
       
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", configuration);
       
       return source;
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
	}		
	
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				
		//auth.userDetailsService(userService).passwordEncoder(this.noOpPasswordEncoder());
		auth.userDetailsService(userService).passwordEncoder(this.bCryptPasswordEncoder());
		
		log.info(auth.toString());
	}
	
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName(CustomCsrfFilter.CSRF_COOKIE_NAME);
		
		return repository;
	}
	
 
    private void logoutSuccessHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException {
 
        response.setStatus(HttpStatus.OK.value());
    }   

}
