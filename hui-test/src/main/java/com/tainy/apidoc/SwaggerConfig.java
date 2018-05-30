package com.tainy.apidoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(""))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder()
				.title("API")
				.termsOfServiceUrl("http://localhost:8080/hui_test/swagger-ui.html")
				.description("Test")
				.version("3.2")
				.build();

	}

}
