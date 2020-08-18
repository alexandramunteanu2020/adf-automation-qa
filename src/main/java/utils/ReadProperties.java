package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    File file = new File("./tests_configuration.properties");

    FileInputStream fileInputStream = null;
    Properties properties = new Properties();

    public ReadProperties() {
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return properties.getProperty("baseUrl");
    }

    public String getUrlFiles() {
        return properties.getProperty("fileUrl");
    }
}
