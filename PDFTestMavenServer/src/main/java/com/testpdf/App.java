package com.testpdf;

import com.testpdf.properties.FileProperties;
import com.testpdf.service.PDFService;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App 
{
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static void main( String[] args ){
        try {
            FileProperties fileProperties = new FileProperties();
            FileHandler fileHandler = fileProperties.getFileHandler();
            logger.addHandler(fileHandler);
            PDFService pdfService = new PDFService();
            pdfService.blankPage();

        } catch (Exception e) {
            String name = new Object(){}.getClass().getEnclosingMethod().getName();
            logger.logp(Level.WARNING, App.class.getName(), name, e.getMessage());
        }
    }
}
