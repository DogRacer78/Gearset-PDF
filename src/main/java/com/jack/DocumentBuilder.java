package com.jack;

import java.util.ArrayList;

import com.itextpdf.io.font.constants.FontStyles;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

public class DocumentBuilder {
     // arbritary values
    private int LARGE_FONT_SIZE = 30;
    private int NORMAL_FONT_SIZE = 10;
    private float INDENT_MULTIPLIER = 20.0f;

    private Style normalFont;
    private Style italicFont;
    private Style boldFont;

    private Style currentStyle;
    private Paragraph currentParagraph;
    private float currentIndent = 0.0f;
    private float currentFontSize;

    private ArrayList<Paragraph> paragraphs = new ArrayList<>();

    public DocumentBuilder() {
        // normal font
        this.normalFont = new Style();
        this.italicFont = new Style().simulateItalic();
        this.boldFont = new Style().simulateBold();

        this.currentStyle = this.normalFont;
        this.currentFontSize = this.NORMAL_FONT_SIZE;

        this.currentParagraph = new Paragraph();
        paragraphs.add(this.currentParagraph);
    }

    public void setLargeFontSize() {
        this.currentFontSize = this.LARGE_FONT_SIZE;
    }

    public void setNormalFontSize() {
        this.currentFontSize = this.NORMAL_FONT_SIZE;
    }

    public void setRegularFont() {
        this.currentStyle = this.normalFont;
    }

    public void setItalic() {
        this.currentStyle = this.italicFont;
    }

    public void setBold() {
        this.currentStyle = this.boldFont;
    }

    public void setIndent(float amount) {
        this.addParagraph();
        this.currentIndent += amount;
        this.currentParagraph.setMarginLeft(this.currentIndent * INDENT_MULTIPLIER);
    }

    public void setFill() {
        this.addParagraph();
        this.currentParagraph.setTextAlignment(TextAlignment.JUSTIFIED);
    }

    public void setNoFill() {
        this.addParagraph();
        this.currentParagraph.setTextAlignment(TextAlignment.LEFT);
    }

    public void addText(String text) {
        this.currentParagraph.add(new Text(text).addStyle(currentStyle).setFontSize(this.currentFontSize));
    }

    public void addParagraph() {
        if (!this.currentParagraph.getChildren().isEmpty()) {
            this.currentParagraph = new Paragraph();
            this.paragraphs.add(this.currentParagraph);
        }        
    }

    public Paragraph getCurrentParagraph() {
        return this.currentParagraph;
    }

    public ArrayList<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    public Style getCurrentStyle() {
        return this.currentStyle;
    }

    public float getCurrentIndent() {
        return this.currentIndent;
    }

    public float getCurrentFontSize() {
        return this.currentFontSize;
    }
}
