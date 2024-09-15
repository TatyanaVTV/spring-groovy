package ru.vtvhw.scopes;

public class MobileApp {
    private String name;

    private MobileApp(String name) {
        this.name = name;
    }

    public static MobileApp createMobileApp(String name) {
        System.out.printf("Creating new MobileApp with name '%s' via factory-method.%n", name);
        return new MobileApp(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void doSomeInit() {
        System.out.printf("Doing some init after creating new MobileApp.%n");
    }

    @Override
    public String toString() {
        String baseStr = super.toString();
        return String.format("MobileApp '%s' [%s]", name, baseStr);
    }
}
