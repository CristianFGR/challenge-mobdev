package com.mobdev.challengemobdev.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 *
 * @author Cristian Gonzalez Rojas (cristian.gonzalez.rojas@gmail.com)
 * @version 0.0.1
 * @since 0.0.1
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${app.version}")
    private String version;

    /**
     * @return docker for swagger
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.mobdev"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiEndPointsInfo())
                .tags(new Tag("Microservices Methods",
                        "Operations for manage with Microservices"));
    }

    /**
     * @return main swagger documentation information
     */
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Challenge Mobdev")
                .description("Microservices consumes API Rick and Morty")
                .contact(new Contact("Cristian F Gonzalez Rojas", "", "hello@template.com"))
                .version(version)
                .build();
    }
}
