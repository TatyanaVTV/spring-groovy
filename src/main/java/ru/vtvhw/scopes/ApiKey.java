package ru.vtvhw.scopes;

public class ApiKey {
    private String value;

    public ApiKey(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("API: '%s' [%s]", value, super.toString());
    }
}
