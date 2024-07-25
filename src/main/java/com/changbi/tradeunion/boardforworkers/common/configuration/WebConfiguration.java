package com.changbi.tradeunion.boardforworkers.common.configuration;

import com.changbi.tradeunion.boardforworkers.common.interceptor.ClientHeaderMenuInterceptor;
import com.changbi.tradeunion.boardforworkers.common.interceptor.PrivateBoardAccessInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final ClientHeaderMenuInterceptor clientHeaderMenuInterceptor;
    private final PrivateBoardAccessInterceptor privateBoardAccessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientHeaderMenuInterceptor)
                .order(1)
                .excludePathPatterns("/admin/**/**")
                .addPathPatterns("/**");


        /*TODO 페이지 읽기 권한 기능 협의 필요 사
        registry.addInterceptor(privateBoardAccessInterceptor)
                .order(2)
                .addPathPatterns("/board/private/**");*/
    }


}
