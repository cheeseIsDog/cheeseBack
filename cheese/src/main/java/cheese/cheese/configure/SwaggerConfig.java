package cheese.cheese.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Cheese")
                .description("API List")
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cheese.cheese.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

}
