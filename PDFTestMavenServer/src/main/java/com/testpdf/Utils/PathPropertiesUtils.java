package com.testpdf.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

public class PathPropertiesUtils {

    private static final String DEFAULT_PATH = "PDFTestMavenServer/src/main/resources/path.properties";
    private static final String FILE_NAME = "path.properties";
    private static final String KEY = "VIAFIRMA_PATH";

    private final Properties properties;

    PathPropertiesUtils(String filename) {
        properties = new Properties();
        try {
            properties.load(new FileReader(filename));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.ALL,
                    "Something went wrong while loading the file" + e.getMessage());
        }
    }

    public PathPropertiesUtils() {
        properties = new Properties();
        try {
            properties.load(new FileReader(PathPropertiesUtils.DEFAULT_PATH));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.ALL, "Something went wrong while loading the file" + e.getMessage());
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key, "There is no key in that properties file");
    }

    public void updateProperty(String newValue) throws Exception {
        String filename = PathPropertiesUtils.FILE_NAME;
        FileInputStream input = new FileInputStream(filename);
        Properties property = new Properties();
        property.load(input);
        input.close();

        FileOutputStream output = new FileOutputStream(filename);
        property.setProperty(PathPropertiesUtils.KEY, newValue);
        property.store(output, null);
        output.close();
    }
    
    public Properties getProperty() {
        return this.properties;
    }
}
