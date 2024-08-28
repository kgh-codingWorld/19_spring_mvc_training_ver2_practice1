package com.application.traningVer2.practice1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
// 1. 해당 클래스를 설정 파일로 사용하기 위해 지정(Spring IoC 컨테이너가 빈을 생성하고 등록함)
// 2. 싱글톤 범위를 보장함으로써 해당 클래스 내의 @Bean이 여러 번 호출되더라도 항상 동일한 빈 인스턴스를 반환함

@EnableWebSecurity // Spring Security에서 보안 설정 활성화하고 사용자 정의 보안 구성을 제공
public class SecurityConfig {

	@Bean
    public PasswordEncoder passwordEncoder(){
		// BCryptPasswordEncoder 인스턴스를 생성하여 반환
	    // 비밀번호를 암호화(인코딩)할 때 사용된다.
        return new BCryptPasswordEncoder();
    }

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	// HttpSecurity를 통해 웹 보안 설정을 구성
    	
		http.cors().disable()			//cors 방지
			.csrf().disable()			//csrf 방지
			.formLogin().disable()		//기본 로그인페이지 없애기
			.headers().frameOptions().disable();
 
		return http.build();
	}
}
