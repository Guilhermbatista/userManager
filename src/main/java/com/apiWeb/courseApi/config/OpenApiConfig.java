package com.apiWeb.courseApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("RestFull APi with Java 21 and SpringBoot 3").version("v1")
				.description("").termsOfService("https://github.com/Obatista02/")
				.license(new License().name("Apache 2.0").url("https://github.com/Obatista02/")));
	}
}
