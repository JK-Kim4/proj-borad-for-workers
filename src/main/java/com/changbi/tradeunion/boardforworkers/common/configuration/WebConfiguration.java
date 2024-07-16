package com.changbi.tradeunion.boardforworkers.common.configuration;

import com.changbi.tradeunion.boardforworkers.common.interceptor.ClientHeaderMenuInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final ClientHeaderMenuInterceptor clientHeaderMenuInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientHeaderMenuInterceptor)
                .excludePathPatterns("/admin/**/**")
                .addPathPatterns("/**");
    }
}
