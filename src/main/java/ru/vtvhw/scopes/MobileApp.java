package ru.vtvhw.scopes;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MobileApp {
    private String name;

    private MobileApp(@Value("${mobileApp.defaultName}") String name) {
        this.name = name;
    }

    public static MobileApp createMobileApp(@Value("${mobileApp.defaultName}") String name) {
        System.out.printf("Creating new MobileApp with name '%s' via factory-method.%n", name);
        return new MobileApp(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public static void doSomeInit() {
        System.out.printf("Doing some init after creating new MobileApp.%n");
    }

    @Override
    public String toString() {
        String baseStr = super.toString();
        return String.format("MobileApp '%s' [%s]", name, baseStr);
    }
}
