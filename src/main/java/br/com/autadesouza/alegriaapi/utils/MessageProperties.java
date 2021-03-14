package br.com.autadesouza.alegriaapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageProperties {

    @Autowired
    private MessageSource messageSource;

    public String getFormattedMessage(final String key) {
        return this.messageSource.getMessage(key, null, getLocale());
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public String getFormattedMessage(final String key, final Object[] args) {
        return this.messageSource.getMessage(key, args, getLocale());
    }

    public String getFormattedMessage(final String key, String locale) {
        return this.messageSource.getMessage(key, null, Locale.forLanguageTag(locale));
    }
}
