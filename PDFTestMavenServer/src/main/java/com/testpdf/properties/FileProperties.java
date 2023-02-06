package com.testpdf.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

public class FileProperties {

    public FileHandler getFileHandler() throws IOException {

           Properties defaultProps = new Properties();
            FileInputStream in = new FileInputStream("PDFTestMavenServer/src/main/resources/logging.properties");
            defaultProps.load(in);
            String logPath = defaultProps.getProperty("log.path");
        return new FileHandler(logPath, true);
    }
}
