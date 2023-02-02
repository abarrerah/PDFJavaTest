package com.testpdf.Utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PDFUtils {
    
    public List<File> listFileForFolder() throws Exception {
        File folder = new File("G:\\PDFJavaTest\\example_folder");
        File [] listOfFiles = folder.listFiles();
        List<File> files = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                files.add(file);
            }
        }

        return files;
    }

   public List<PDDocument> checkPDFOnList(List<File> files)  {
        List<PDDocument> pdfList = new ArrayList<>();
        for(File file : files) {
            try {
                PDDocument pdDocument = Loader.loadPDF(file);
                pdfList.add(pdDocument);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
        return  pdfList;
   }

   public List<PDDocument> checkIfPdfHasBlankPage(List<PDDocument> files) throws IOException {
        List<PDDocument> noBlankList = new ArrayList<PDDocument>();
        for(PDDocument file : files) {
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

        return noBlankList;
   }

   public List<PDDocument> addBlankPage(List<PDDocument> files) throws IOException {
        List<PDDocument> addedPagesPdfs = new ArrayList<PDDocument>();
        for(PDDocument file : files) {
            PDPage page = new PDPage();
            file.addPage(page);
            PDDocumentInformation info = file.getDocumentInformation();
            file.save("G:\\PDFJavaTest\\example_folder\\" + info.getTitle() + ".pdf");
            addedPagesPdfs.add(file);

        }
        return addedPagesPdfs;
   }
 }
