package ru.vtvhw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import ru.vtvhw.scopes.MobileApp;
import ru.vtvhw.scopes.MobileMarket;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new GenericGroovyApplicationContext(
                "classpath:config/SpringConfig.groovy"
        );
        mobileAppsPublishing(context);
        ((GenericGroovyApplicationContext) context).close();
    }

    private static void mobileAppsPublishing(ApplicationContext context) {
        MobileMarket market =  context.getBean("market", MobileMarket.class);
        System.out.printf("%n-== First request of singleton bean '%s' ==-%n%n", market.getName());
        MobileApp app;
        for (int i = 1; i < 6; i++) {
            app = context.getBean("mobileApp", MobileApp.class);
            app.setName(app.getName() + " #" + i);
            market.publish(app);
        }
        System.out.println();
        market.printPublishedApps();

        System.out.printf("%n-== Second request of singleton bean '%s' ==-%n%n", market.getName());
        market = context.getBean("market", MobileMarket.class);
        market.printPublishedApps();
    }
}