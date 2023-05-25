package com.healthbest.api.common.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi healthBestApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("건강 최고당 API v1")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public OpenAPI healthBestOpenAPI() {
        Info info = new Info()
                .title("건강최고당 API")
                .description("건강 최고당 API 명세서");

        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement addSecurityItem = new SecurityRequirement();
        addSecurityItem.addList("JWT");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JWT", bearerAuth))
                .addSecurityItem(addSecurityItem)
                .info(info);
    }
}
