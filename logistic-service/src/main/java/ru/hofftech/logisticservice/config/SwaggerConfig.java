package ru.hofftech.logisticservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${TAG:undefined}") String appVersion, @Value("${swagger.url.path:/}") String swaggerUrlPath) {
        return new OpenAPI()
                .addServersItem(new Server().url(swaggerUrlPath))
                .info(new Info()
                        .title("Logistic Service")
                        .version(appVersion)
                        .description("Logistic Service"));
    }
}
