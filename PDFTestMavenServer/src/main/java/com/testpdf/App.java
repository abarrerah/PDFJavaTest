package com.testpdf;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.testpdf.Utils.PDFUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        //PathPropertiesUtils pathProperties = new PathPropertiesUtils();
        //Logger.getLogger("ReadWithFileReader").log(Level.INFO, pathProperties.readProperty("VIAFIRMA_PATH"));
        PDFUtils pdfUtils = new PDFUtils();
        List<File> files = pdfUtils.listFileForFolder();
        List<PDDocument> pdfList = pdfUtils.checkPDFOnList(files);
        List<PDDocument> noBlankPdf = pdfUtils.checkIfPdfHasBlankPage(pdfList);
        List<PDDocument> blankedPdf = pdfUtils.addBlankPage(noBlankPdf);

    }
}
