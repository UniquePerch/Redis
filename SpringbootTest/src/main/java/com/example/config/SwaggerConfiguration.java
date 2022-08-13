package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .contact(new Contact("willow", "https://github.com/UniquePerch", "lu1563814178@hotmail.com"))
                .title("图书管理系统 - 在线API接口文档")
                .description("欢迎访问")
                .version("1.0")
                .build();
    }
}