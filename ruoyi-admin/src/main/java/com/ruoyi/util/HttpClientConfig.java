package com.ruoyi.util;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClientBuilder httpClientBuilder() {
        return HttpClientBuilder.create();
    }

    @Bean
    public HttpClient httpClient(HttpClientBuilder builder) {
        return new HttpClient(builder);
    }
}
