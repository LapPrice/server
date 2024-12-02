package com.lapprice.lapprice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {


	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("My API Documentation")
				.version("1.0")
				.description("This is a sample Spring Boot RESTful service using OpenAPI"));
	}
}
