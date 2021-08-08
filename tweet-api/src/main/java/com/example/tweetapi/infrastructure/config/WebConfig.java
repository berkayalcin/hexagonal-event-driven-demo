package com.example.tweetapi.infrastructure.config;

import com.example.tweetapi.infrastructure.filter.XSSFilter;
import com.example.tweetapi.infrastructure.interceptor.CorrelationIdInterceptor;
import com.example.tweetapi.infrastructure.interceptor.ExecutorUserInterceptor;
import com.example.tweetapi.infrastructure.interceptor.LogExecutionInterceptor;
import com.example.tweetapi.infrastructure.interceptor.RestTemplateErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedOriginPatterns("*");
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new CorrelationIdInterceptor(applicationName));
        registry.addInterceptor(new ExecutorUserInterceptor());
        registry.addInterceptor(new LogExecutionInterceptor());
    }

    @Bean
    public FilterRegistrationBean<Filter> xssPreventFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new XSSFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public RestTemplate restTemplate() {
        var rest = new RestTemplateBuilder();
        return rest.errorHandler(new RestTemplateErrorHandler()).build();
    }
}
