package ru.vtvhw.scopes;

import java.util.ArrayList;
import java.util.List;

public class RuStoreMarket implements MobileMarket {
    private String name;
    private List<MobileApp> apps = new ArrayList<>();

    private ApiKey apiKey;

    public RuStoreMarket(ApiKey apiKey) {
        this.name = "RuStore";
        this.apiKey = apiKey;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void publish(MobileApp app) {
        apps.add(app);
    }

    @Override
    public void printPublishedApps() {
        System.out.printf("%s: %n", this);
        apps.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return String.format("Market '%s' (%s) [%s]", name, apiKey, super.toString());
    }
}