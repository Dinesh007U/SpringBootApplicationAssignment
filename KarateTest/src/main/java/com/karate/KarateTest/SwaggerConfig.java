//package com.application.customer;
//
//import java.util.Collections;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//	 @Bean
//	    public Docket api() {
//	        return new Docket(DocumentationType.SWAGGER_2)
//	            .select()
//	            .paths(PathSelectors.ant("/customer/*"))
//	            .apis(RequestHandlerSelectors.basePackage("com.application.customer")) 
//	            
//	            .build()
//         .apiInfo(apiCustomData());
//	    }
//	 
//	 
//	
//		
//
//	 private ApiInfo apiCustomData() {
//		 return new ApiInfo(
//				 "Customer",
//				 "Customer Details API",
//				 "1.0",
//				 "Customer Details Service Terms",
//				 new Contact("UD","https://www.linkedin.com/in/dinesh-ramasamy-31871118a/","dineshruram@gmail.com"),				 			 
//				 "Ud License",
//				 "https://www.linkedin.com/in/dinesh-ramasamy-31871118a/",
//				 Collections.emptyList()
//				 
//				 
//				 );
//				 
//	 
//	}
//	
//				
//}


