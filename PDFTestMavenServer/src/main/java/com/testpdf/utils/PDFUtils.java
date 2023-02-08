package com.testpdf.utils;

import com.testpdf.App;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PDFUtils {
    private static final Logger logger = Logger.getLogger(PDFUtils.class.getName());

    public List<File> listFileForFolder(String folderPath) throws Exception {
        File folder = new File(folderPath);
        FileFilter pdfFileFilter = file -> {
            if (file.getName().endsWith(".pdf")) {
                return true;
            }
            return false;
        };
        File[] listOfFiles = folder.listFiles(pdfFileFilter);
        if (listOfFiles.length == 0) {
            String name = new Object() {
            }.getClass().getEnclosingMethod().getName();
            logger.logp(Level.WARNING, PDFUtils.class.getName(), name, "Empty folder/no pdf files inside.");
        }

        return new ArrayList<>(Arrays.asList(listOfFiles));
    }

    public List<PDDocument> checkPDFOnList(List<File> files) {
        List<PDDocument> pdfList = new ArrayList<>();
        try {
            for (File file : files) {
                try {
                    PDDocument pdDocument = Loader.loadPDF(file);
                    pdfList.add(pdDocument);
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            }
        } catch (Exception e) {
            String name = new Object() {
            }.getClass().getEnclosingMethod().getName();
            logger.logp(Level.WARNING, PDFUtils.class.getName(), name, e.getMessage());
        }

        return pdfList;
    }

    public List<PDDocument> checkIfPdfHasBlankPage(List<PDDocument> files) {
        List<PDDocument> noBlankList = new ArrayList<PDDocument>();
        try {
            for (PDDocument file : files) {
                int count = file.getNumberOfPages();
                PDPage lastPage = file.getPage(count - 1);
                Iterable<COSName> xObjects = lastPage.getResources().getXObjectNames();
                Iterable<COSName> fonts = lastPage.getResources().getFontNames();
                int xObjectsSize = (int) xObjects.spliterator().getExactSizeIfKnown();
                int fontsSize = (int) fonts.spliterator().getExactSizeIfKnown();
                if (xObjectsSize != 0 || fontsSize != 0) {
                    noBlankList.add(file);
                }
            }
        } catch (Exception e ) {
            String name = new Object() {}.getClass().getEnclosingMethod().getName();
            logger.logp(Level.WARNING, PDFUtils.class.getName(), name, e.getMessage());
        }
        return noBlankList;
    }
    public void addBlankPage(List<PDDocument> files) {
        try {
            for (PDDocument file : files) {
                PDPage page = new PDPage();
                file.addPage(page);
                PDDocumentInformation info = file.getDocumentInformation();
                String checkPdfName = new File("").getAbsolutePath() + info.getTitle() + ".pdf";
                File checkPdfExist = new File(checkPdfName);
                String newPdfName = (checkPdfExist.exists() && !checkPdfExist.isDirectory()) ? new File("").getAbsolutePath() + info.getTitle() + "_" + UUID.randomUUID() + ".pdf" : new File("").getAbsolutePath() + info.getTitle() + ".pdf";
                file.save(newPdfName);
            }
        } catch (Exception e) {
            String name = new Object() {}.getClass().getEnclosingMethod().getName();
            logger.logp(Level.WARNING, PDFUtils.class.getName(), name, e.getMessage());
        }
    }
}
