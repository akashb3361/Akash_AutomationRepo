package dataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String homePageUrl;

    public static void loadProperties() throws IOException {

        Properties envProperties = new Properties();

        envProperties.load(new FileInputStream("src/test/resources/environment/env.properties"));

        homePageUrl = envProperties.getProperty("homePage_URL");
    }

}