package com.testpdf.service;

import com.testpdf.App;
import com.testpdf.utils.PDFUtils;
import com.testpdf.utils.PathUtils;
import com.testpdf.properties.FileProperties;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFService {

    private static final Logger logger = Logger.getLogger(PDFService.class.getName());

    public void blankPage() throws Exception {
        FileProperties fileProperties = new FileProperties();
        FileHandler fileHandler = fileProperties.getFileHandler();
        logger.addHandler(fileHandler);
        try {
            PathUtils pUtils = new PathUtils();
            String path = pUtils.getFolderPath();
            PDFUtils pdfUtils = new PDFUtils();
            List<File> files = pdfUtils.listFileForFolder(path);
            List<PDDocument> pdfList = pdfUtils.checkPDFOnList(files);
            List<PDDocument> noBlankPdf = pdfUtils.checkIfPdfHasBlankPage(pdfList);
            pdfUtils.addBlankPage(noBlankPdf);
        } catch (Exception e) {
            String name = new Object(){}.getClass().getEnclosingMethod().getName();
            logger.logp(Level.WARNING, App.class.getName(), name, e.getMessage());
        }
    }
}
