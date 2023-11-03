package kr.co.won.rentalcardservice.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;


//@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
/*
    @Bean
    public Docket swaggerDocsConfiguration() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) // swagger 에서 제공해주는 기본 응답 코드를 표시할 것이면, true 설정
                .select()
                .apis(RequestHandlerSelectors.basePackage("kr.co.won"))
                .paths(PathSelectors.any())
                .build();
    }*/

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()

                .info(new Info().title("Rental Card Service")
                        .description("book rental card service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.txt")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation"));
    }
}
