package com.tech.prjm09.util;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.CacheControl;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스의 경로를 설정하고, 캐시 제어를 설정합니다.
        registry.addResourceHandler("/files/**")
                .addResourceLocations("classpath:/static/files/") // 리소스 폴더 경로
                .setCacheControl(CacheControl.noCache()) // 캐시 무효화
                .resourceChain(true); // 리소스 캐시를 비활성화하고 매번 새로 로딩
    }
}
