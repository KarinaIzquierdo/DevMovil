
package com.app.backend.config;

import org.springframework.contexy.annotation.Configuration;
import org.springframework.web.Servlet.config.annotation.CorsRegistrty;
import org.springframework.web.Servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebvcConf {

    @Override
    public void addCorsMappings (CorsRegistry registry) {
            registry.addMaping("/api/**")
                     .allowedOrigins("*")
                     .allowedMethods("GET","POST","DELETE","PUT","OPTIONS")
                     .allowerdHeaders("*")
                     .allowedCredentials(true);
    }
    
}

