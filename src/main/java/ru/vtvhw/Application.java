package ru.vtvhw;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vtvhw.scopes.MobileApp;
import ru.vtvhw.scopes.MobileMarket;

public class Application {
    public static void main(String[] args) {
        /** Used when parts of app config were in applicationContext.xml
         * Remained here in learning purposes.
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );*/

        /** Used when config scanned classes for annotations (@Component, etc.)
         * Remained here in learning purposes.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
             SpringAutoScanConfig.class
        );*/
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
        mobileAppsPublishing(context);
        context.close();
    }

    /** Used when config scanned classes for annotations (@Component, etc.)
     * Remained here in learning purposes
    private static String getMarketName() {
        Properties properties = new Properties();
        try(InputStream propertiesFileIS = Application.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(propertiesFileIS);
            return properties.getProperty("market.name", "ruStore");
        } catch (IOException ex) {
            return "ruStore";
        }
    }.*/

    private static void mobileAppsPublishing(AnnotationConfigApplicationContext context) {
        /**Used when config scanned classes for annotations (@Component, etc.)
         * Remained here in learning purposes.
         String marketName = getMarketName();
         MobileMarket market = context.getBean(marketName, MobileMarket.class);*/

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
//        market = context.getBean(marketName, MobileMarket.class);
        market.printPublishedApps();
    }
}