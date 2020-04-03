package utils;

public class Properties {

    private static final ConfigReader propertiesReader = new ConfigReader();
    public static final String execType = propertiesReader.getExecutionType();
}
