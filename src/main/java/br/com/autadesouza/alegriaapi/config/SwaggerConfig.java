package br.com.autadesouza.alegriaapi.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.base.Predicates.not;

/**
 *
 * @author Ellcyo Castro
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.autadesouza.alegriaapi.controller"))
                .paths(paths())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "ALEGRIA CRISTÃ - API Documentation",
                "Documentação geral das APIs disponíveis para consumo do portal da Alegria Cristã...",
                "http://www.portaldaalegria.com.br",
                "Aqui é só aegria",
                new Contact("Alegria Cristã", "www.portaldaalegria.com.br", "suporte@portaldaalegria.com.br"),
                "Licença da API",
                "www.weconn.com.br",
                Collections.emptyList()
        );

        return apiInfo;
    }

    private Predicate<String> paths() {
        return not(PathSelectors.regex("/wclients.*"));
    }
}