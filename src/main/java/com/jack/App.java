package com.jack;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import com.itextpdf.layout.element.Paragraph;

public class App 
{
    public static void main(String[] args) throws IOException {
        ArrayList<String> inputFile = readInputFile("./input.txt");
        
        PdfCreator pdfCreator = new PdfCreator(inputFile, new DocumentBuilder());
        ArrayList<Paragraph> documentData = pdfCreator.createDocument();
        PdfHelper.createPDF(documentData, "./test.pdf");
    }

    private static ArrayList<String> readInputFile(String path) {
        ArrayList<String> lines = new ArrayList<>();
        Path filePath = Path.of(path);

        try {
            Scanner scanner = new Scanner(filePath.toFile());

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine().replace("\n", "").replace("\r", ""));
            }

            scanner.close();

            System.out.println(String.format("Read file %s", filePath.toString()));
        }
        catch (IOException e) {
            // log if we couldnt read the file
            System.err.println("Unable to read lines in file " + filePath.toString());
            System.exit(-1);
        }

        return lines;
    }
}
