package ru.hofftech.logistictelegrambotservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.hofftech.logistictelegrambotservice.service.LogisticService;

@Configuration
public class RestClientConfig {

    @Value("${services.logistic-service-url}")
    private String logisticServiceUrl;

    @Bean
    public LogisticService logisticService() {
        RestClient restClient = RestClient.builder()
                .baseUrl(logisticServiceUrl)
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(LogisticService.class);
    }
}
