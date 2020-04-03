package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties properties = new Properties();

    public ConfigReader() {

        try {
            String propertiesFilePath = "config.properties";
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(propertiesFilePath);
            properties.load(stream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getExecutionType(){
        return properties.getProperty("exectype");
    }
}
