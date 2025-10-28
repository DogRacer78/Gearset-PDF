package com.jack;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 * Used to create a PDF document
 */
public class PdfHelper {

    public static void createPDF(ArrayList<Paragraph> paragraphs, String outputPath) throws IOException {
        Document document = new Document(new PdfDocument(new PdfWriter(outputPath)));

        for (Paragraph p : paragraphs) {
            document.add(p);
        }

        document.close();
    }

}
