package com.jack;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.layout.element.Paragraph;

public class PdfCreator {

    private DocumentBuilder documentBuilder;
    private ArrayList<String> commands;

    public PdfCreator(ArrayList<String> commands, DocumentBuilder documentBuilder) throws IOException {
        this.documentBuilder = documentBuilder;

        this.commands = commands;
    }

    public ArrayList<Paragraph> createDocument() {

        for (String line : this.commands) {
            System.out.println(line);

            if (line.equals(".paragraph")) {
                this.documentBuilder.addParagraph();
            }
            else if (line.equals(".normal")) {
                this.documentBuilder.setNormalFontSize();
            }
            else if (line.equals(".italic")) {
                this.documentBuilder.setItalic();
            }
            else if (line.equals(".regular")) {
                this.documentBuilder.setRegularFont();
            }
            else if (line.equals(".large")) {
                this.documentBuilder.setLargeFontSize();
            }
            else if (line.equals(".bold")) {
                this.documentBuilder.setBold();
            }
            else if (line.contains(".indent")) {

                float indentValue = parseIndentValue(line);

                this.documentBuilder.setIndent(indentValue);
            }
            else if (line.equals(".fill")) {
                this.documentBuilder.setFill();
            }
            else if (line.equals(".nofill")) {
                this.documentBuilder.setNoFill();
            }
            else {
                // adding one line to account for text on different lines in the file
                documentBuilder.addText(line + " ");
            }
        }

        return documentBuilder.getParagraphs();
    }

    /**
     * Returns the indent value from the indent, if the indent value is invalid it will indent by 0 and print ant warning
     * @param line Line to parse
     * @return Indent Value
     */
    private float parseIndentValue(String line) {
        String value = line.split(" ")[1];

        try {
            return Float.parseFloat(value);
        }
        catch (NumberFormatException e) {
            System.err.println(String.format("Cannot interpret line %s", line));
            return 0.0f;
        }
    }

}
