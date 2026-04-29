package com.antaehoo.handwriting.config; // <-- 이 줄을 맨 위에 추가하세요!

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")          // 모든 경로 (예: /api/**)에 대해
                .allowedOrigins("*")        // 모든 출처(포트 무관) 허용
                .allowedMethods("*")        // GET, POST, PUT, DELETE 등 모든 HTTP 메서드 허용
                .allowedHeaders("*");       // 모든 헤더 허용
    }
}