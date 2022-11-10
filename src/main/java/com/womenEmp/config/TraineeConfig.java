package com.womenEmp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;




@Configuration
@EnableSwagger2
public class TraineeConfig {
	
	
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("com.womenEmp.controller").apiInfo(apiInfo()).select()
				.paths(regex("/.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Women Empowerment Service")
				.description("Sample Documentation Generateed Using SWAGGER2 for our Book Rest API")
				.termsOfServiceUrl("https://www.google.co.in")
				.license("PVT_ltd")
				.licenseUrl("https://www.google.co.in").version("1.0").build();
	}
}