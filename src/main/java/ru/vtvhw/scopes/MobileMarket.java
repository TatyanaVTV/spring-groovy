package ru.vtvhw.scopes;

import jakarta.annotation.PreDestroy;

public interface MobileMarket {
    String getName();
    void publish(MobileApp app);
    void printPublishedApps();

    @PreDestroy
    default void doDestroy() {
        System.out.printf("%nDestroying Market bean '%s'.%n", getName());
    }
}
