package com.meowu.account.portal.service.commons.config;

import com.meowu.commons.utils.utils.GsonUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

    private Charset CHARSET = Charset.forName("utf-8");

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //gson convert
        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setDefaultCharset(CHARSET);
        gsonConverter.setGson(GsonUtils.getGson());

        //string convert
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(CHARSET);

        converters.add(gsonConverter);
        converters.add(stringConverter);
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .maxAge(-1);
    }
}
