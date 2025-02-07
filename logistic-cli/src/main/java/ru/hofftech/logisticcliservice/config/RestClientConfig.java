package ru.hofftech.logisticcliservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.hofftech.logisticcliservice.service.BillingService;
import ru.hofftech.logisticcliservice.service.LogisticService;

@Configuration
public class RestClientConfig {

    @Value("${services.logistic-service-url}")
    private String logisticServiceUrl;

    @Value("${services.logistic-billing-url}")
    private String logisticBillingUrl;

    @Bean
    public LogisticService logisticService() {
        RestClient restClient = RestClient.builder()
                .baseUrl(logisticServiceUrl)
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(LogisticService.class);
    }

    @Bean
    public BillingService billingService() {
        RestClient restClient = RestClient.builder()
                .baseUrl(logisticBillingUrl)
                .build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return httpServiceProxyFactory.createClient(BillingService.class);
    }
}
