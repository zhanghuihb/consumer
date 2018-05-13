package com.tainy.console.apidoc;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableSwagger
public class SwaggerConfig {
	
	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
	  this.springSwaggerConfig = springSwaggerConfig;
	}

	@Bean //Don't forget the @Bean annotation
	public SwaggerSpringMvcPlugin customImplementation(){
	  return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
	}

	private ApiInfo apiInfo() {
	  ApiInfo apiInfo = new ApiInfo(
	          "商户API",
	          "My Apps API Description",
	          "My Apps API terms of service",
	          "My Apps API Contact Email",
	          "My Apps API Licence Type",
	          "My Apps API License URL"
	    );
	  return apiInfo;
	}

}
