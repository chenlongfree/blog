package com.loong.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问  /static/下的即为 /statice/下的目录
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        //访问  /pic/下的路径即为 /upload/Pic/下的路径
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/upload/");
    }
}