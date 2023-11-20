package com.marketbook.config

import io.swagger.v3.oas.models.info.Contact
import org.springdoc.core.models.GroupedOpenApi
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SwaggerConfig : WebMvcConfigurer {

    //TODO: swagger url :   http://localhost:8080/swagger-ui/index.html#/

    init {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(
            org.springframework.web.bind.annotation.RestController::class.java
        )
    }

//    @Bean
//    fun apiInfoCustomizer(): OpenApiCustomizer {
//        return OpenApiCustomizer { apiInfo ->
//            apiInfo.info.title("Título Personalizado da API")
//                .description("Descrição Personalizada da API")
//                .version("1.0.0")
//                .contact(
//                    Contact().apply {
//                        name("Seu Nome")
//                        email("seu@email.com")
//                    }
//                )
//        }
//    }

    @Bean
    fun customApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("api")
            .packagesToScan("com.marketbook.controller")
            .addOpenApiCustomizer {apiInfo ->
                apiInfo.info.title("MarketBook")
                    .description("APIs desenvolvidas no curso Udemy: Kotlin do zero ao avançado")
                    .version("1.0.0")
                    .contact(
                        Contact().apply {
                            name("Alex Rosa")
                            email("alexr@gec.inatel.br")
                        }
                    )
            }
            .build()
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/")
        registry.addResourceHandler("/swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html")
    }

}