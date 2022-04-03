package io.gig.catchreview.api.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author : JAKE
 * @date : 2022/04/03
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String version;
    private String title;

    @Bean
    public Docket apiV1() {

        version = "V1";
        title = "catchreview API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.gig.catchreview.api.controller.v1"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo(title, version))
                ;
    }

    @Bean
    public Docket apiV2() {

        version = "V2";
        title = "catchreview API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.gig.catchreview.api.controller.v1"))
                .paths(PathSelectors.ant("/api/v2/**"))
                .build()
                .apiInfo(apiInfo(title, version))
                ;
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger로 생성한 API Docs",
                version,
                "www.catchreview.io",
                new Contact("Contact Me", "www.catchreview.io", "dev.jake92@gmail.com"),
                "Licenses",
                "www.catchreview.io");
    }
}
