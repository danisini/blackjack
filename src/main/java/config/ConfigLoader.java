package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static util.CommonConstants.*;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {

        try (InputStream input = ConfigLoader.class.getClassLoader().
                getResourceAsStream(APPLICATION_PROPERTIES)) {

            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }

            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
