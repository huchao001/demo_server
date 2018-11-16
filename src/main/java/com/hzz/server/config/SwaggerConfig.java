package com.hzz.server.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConditionalOnProperty(value = "spring.profiles.swagger", matchIfMissing = true)
@EnableSwagger2
public class SwaggerConfig {
    private static final String scanBasePackage = "com.hzz.server.controller";

    private Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).pathMapping("/")
                .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket mxDataApi() {
        return apiDocket().groupName("mall.hotel").apiInfo(mxDataApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(scanBasePackage)).paths(PathSelectors.any()).build();
    }

    private ApiInfo mxDataApiInfo() {
        Contact contact = new Contact("hzz", "", "");
        return new ApiInfoBuilder().title("Demo接口文档").description("Demo接口文档描述")
                .contact(contact).version("1.0.0").build();
    }

}
