package com.rookie.bigdata.config;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @Class GsonHttpMessageConverterConfiguration
 * @Description 
 * @Author rookie
 * @Date 2023/5/26 11:09
 * @Version 1.0
 */
@Configuration
@ConditionalOnClass(Gson.class)
@ConditionalOnMissingClass("com.fasterxml.jackson.core.JsonGenerator")
@ConditionalOnBean(Gson.class)
public class GsonHttpMessageConverterConfiguration {

    @Bean
    @ConditionalOnBean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }

}