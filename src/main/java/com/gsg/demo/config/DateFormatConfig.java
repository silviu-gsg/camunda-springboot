package com.gsg.demo.config;

import org.camunda.bpm.engine.rest.CustomJacksonDateFormatListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;

import static com.gsg.demo.util.EngineConstants.DEFAULT_GLOBAL_DATE_FORMAT;

@Configuration
public class DateFormatConfig {

    @Bean
    public ServletContextInitializer initializer() {
        return servletContext -> servletContext.setInitParameter(
                "org.camunda.bpm.engine.rest.jackson.dateFormat", DEFAULT_GLOBAL_DATE_FORMAT);
    }

    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
        bean.setListener(new CustomJacksonDateFormatListener());
        return bean;
    }

}
