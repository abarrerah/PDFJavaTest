package com.testpdf;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.testpdf.Service.PDFService;
import com.testpdf.Utils.PDFUtils;
import com.testpdf.Utils.PathUtils;
import com.testpdf.properties.FileProperties;
import org.apache.pdfbox.pdmodel.PDDocument;

public class App 
{
    public static void main( String[] args ){
        Logger logger = Logger.getLogger(App.class.getName());
        try {
            FileProperties fileProperties = new FileProperties();
            FileHandler fileHandler = fileProperties.getFileHandler();
            logger.addHandler(fileHandler);
            PDFService pdfService = new PDFService();
            pdfService.blankPage();
        } catch (Exception nsfe) {
            String name = new Object(){}.getClass().getEnclosingMethod().getName();
            logger.logp(Level.WARNING, App.class.getName(), name, nsfe.getMessage());
        }
    }
}
