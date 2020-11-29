package com.sanix.Spring_Microservices;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

public class Internationalization {

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.CANADA);
        return sessionLocaleResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource=
                new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
