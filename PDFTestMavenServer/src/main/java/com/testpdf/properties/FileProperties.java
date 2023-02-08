package com.testpdf.properties;


import com.testpdf.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class FileProperties {
    private static final Logger logger = Logger.getLogger(FileProperties.class.getName());

    public FileHandler getFileHandler() throws IOException {

        Properties defaultProps = new Properties();
        ClassLoader classLoader = App.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("logging.properties")).getFile());
        FileInputStream in = (FileInputStream) getClass().getResourceAsStream(file.getAbsolutePath());
        defaultProps.load(in);
        String logPath = defaultProps.getProperty("log.path");
        return new FileHandler(logPath, true);
    }
}
