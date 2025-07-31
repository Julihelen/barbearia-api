// package br.com.ifpe.barbearia_api.config;

// import org.springdoc.core.models.GroupedOpenApi;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Contact;
// import io.swagger.v3.oas.models.info.Info;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("Barbearia Api")
//                         .version("1.0")
//                         .description("Api da barbearia")
//                         .contact(new Contact()
//                                 .name("Julia Santana")
//                                 .email("juliahlsantanaa@gmail.com")));
//     }

//     @Bean
//     public GroupedOpenApi customApi() {
//         return GroupedOpenApi.builder()
//                 .group("api")
//                 .pathsToMatch("/api/**")
//                 .pathsToExclude("/error", "/actuator/**")
//                 .build();
//     }
// }
