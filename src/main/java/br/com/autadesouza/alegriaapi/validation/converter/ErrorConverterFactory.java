package br.com.autadesouza.alegriaapi.validation.converter;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Component
public class ErrorConverterFactory {

    @Autowired
    private List<ErrorConverter<?>> errors;

    private final Map<Class<?>, ErrorConverter<?>> map = Maps.newHashMap();

    @PostConstruct
    private void init() {
        errors.stream().forEach(e -> map.put(e.getType(), e));
    }

    public <T> ErrorConverter<T> getConverter(Type type) {
        return (ErrorConverter<T>) map.get(type);
    }
}
