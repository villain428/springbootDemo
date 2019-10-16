package com.ccccit.spring.boot.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {

    @Value("${villain.excel.path}")
    private String excePath;

    public String getExcePath() {
        return excePath;
    }

    public void setExcePath(String excePath) {
        this.excePath = excePath;
    }
}
