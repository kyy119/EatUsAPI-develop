package com.example.eatusapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition") // group 이름을 설정
                .pathsToMatch("/**") // Swagger API 명세서에 기입할 경 -> http://localhost:8080/~~
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("DEMO API")
                        .description("EatUs 프로젝트 API 명세서입니다.")
                        .version("v0.0.1"));
    } // api 명세서에 대한 부분
}
