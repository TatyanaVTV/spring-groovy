package ru.vtvhw.scopes;

public interface MobileMarket {
    String getName();
    void publish(MobileApp app);
    void printPublishedApps();

    default void doDestroy() {
        System.out.printf("%nDestroying Market bean '%s'.%n", getName());
    }
}
