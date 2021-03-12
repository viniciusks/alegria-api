package br.com.autadesouza.alegriaapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class ValidationConfig {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public Validator validator() {
        var validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);

        return validator;
    }
}
