package br.com.autadesouza.alegriaapi.validation.converter;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
public class ConstraintViolationWrapper {

    public Iterator<String> getPaths(final ConstraintViolation<?> violation) {
        final List<String> paths = Lists.newArrayList();

        violation.getPropertyPath().forEach(n -> paths.add(n.getName()));

        return paths.iterator();
    }

    public String getPath(final ConstraintViolation<?> violation) {
        return violation.getPropertyPath().toString();
    }

    public Object getRootBean(final ConstraintViolation<?> violation) {
        return violation.getRootBean();
    }

    public Set<Class<?>> getGroups(final ConstraintViolation<?> violation) {
        return violation.getConstraintDescriptor().getGroups();
    }

    public String getMessage(final ConstraintViolation<?> violation) {
        return violation.getMessage();
    }
}
