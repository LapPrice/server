package com.lapprice.lapprice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // 모든 경로에 대해 허용
					.allowedOrigins("http://localhost:5173") // 프론트엔드 주소
					.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
					.allowedHeaders("*") // 모든 헤더 허용
					.allowCredentials(true); // 쿠키 허용
			}
		};
	}
}
