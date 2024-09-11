package ru.vtvhw.scopes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ApiKey {
    private String value;

    public ApiKey(@Value("${apiKey.value}") String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("API: '%s' [%s]", value, super.toString());
    }
}
