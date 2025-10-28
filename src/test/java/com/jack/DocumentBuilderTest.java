package com.jack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class DocumentBuilderTest {

    DocumentBuilder documentBuilder;

    

    @BeforeEach
    void beforeEach() {
        documentBuilder = new DocumentBuilder();
    }

    @Test
    @DisplayName("Should set currentFontSize to largeTextSize")
    void largeTextSet() {
        documentBuilder.setLargeFontSize();

        assertEquals(30.0f, documentBuilder.getCurrentFontSize());
    }

    @Test
    @DisplayName("Should set font size to normalFontSize")
    void normalFontSize() {
        documentBuilder.setNormalFontSize();

        assertEquals(10.0f, documentBuilder.getCurrentFontSize());
    }

    @Test
    @DisplayName("Should set font to regular style")
    void regularFont() {
        documentBuilder.setRegularFont();

        Style currentStyle = documentBuilder.getCurrentStyle();
        Object isBold = currentStyle.getOwnProperty(Property.BOLD_SIMULATION);
        Object isItalic = currentStyle.getOwnProperty(Property.ITALIC_SIMULATION);

        assertNotNull(currentStyle);
        assertNull(isBold);
        assertNull(isItalic);
    }  

    @Test
    @DisplayName("Should set currentStyle to italics")
    void italicsFont() {
        documentBuilder.setItalic();

        Style currentStyle = documentBuilder.getCurrentStyle();
        boolean res = currentStyle.getOwnProperty(Property.ITALIC_SIMULATION);

        assertTrue(res);
    }

    @Test
    @DisplayName("Should set currentStyle to bold")
    void boldFont() {
        documentBuilder.setBold();

        Style currentStyle = documentBuilder.getCurrentStyle();
        boolean res = currentStyle.getOwnProperty(Property.BOLD_SIMULATION);

        assertTrue(res);
    }

    @Test
    @DisplayName("Should set currentStyle to indent and set the left margin on the paragraph with the correct amount")
    void indent() {
        documentBuilder.setIndent(1);

        float currentIndent = documentBuilder.getCurrentIndent();
        Paragraph currentParagraph = documentBuilder.getCurrentParagraph();

        assertEquals(1.0f, currentIndent);
        assertEquals(1.0f * 20.0f, currentParagraph.getMarginLeft().getValue());
    }

    @Test
    @DisplayName("Should set currentParagraph to use Justify")
    void fill() {
        documentBuilder.setFill();

        Paragraph currentParagraph = documentBuilder.getCurrentParagraph();
        TextAlignment res = currentParagraph.getProperty(Property.TEXT_ALIGNMENT);

        assertEquals(TextAlignment.JUSTIFIED, res);
    }

    @Test
    @DisplayName("Should set currentParagraph to use left justify")
    void nofill() {
        documentBuilder.setNoFill();

        Paragraph currentParagraph = documentBuilder.getCurrentParagraph();
        TextAlignment res = currentParagraph.getProperty(Property.TEXT_ALIGNMENT);

        assertEquals(TextAlignment.LEFT, res);
    }

    @Test
    @DisplayName("Should create new paragraph and add to paragraphs array when current paragraph is not empty")
    void paragraphNotEmpty() {
        documentBuilder.addText("Test text");
        Paragraph oldParagraph = documentBuilder.getCurrentParagraph();

        documentBuilder.addParagraph();

        Paragraph currentParagraph = documentBuilder.getCurrentParagraph();

        assertFalse(oldParagraph == currentParagraph);
        assertEquals(2, documentBuilder.getParagraphs().size());
    }

    @Test
    @DisplayName("Should not create new paragraph when the current paragraph is empty")
    void paragraphEmpty() {
        Paragraph oldParagraph = documentBuilder.getCurrentParagraph();

        documentBuilder.addParagraph();

        Paragraph currentParagraph = documentBuilder.getCurrentParagraph();

        assertTrue(oldParagraph == currentParagraph);
        assertEquals(1, documentBuilder.getParagraphs().size());
    }

    @Test
    @DisplayName("Should add text with current style and size to paragrpah")
    void addText() {
        documentBuilder.setNormalFontSize();
        documentBuilder.setBold();
        documentBuilder.addText("Test Text");

        Paragraph currentParagraph = documentBuilder.getCurrentParagraph();
        Text currentText = (Text)(currentParagraph.getChildren().get(0));
        boolean isBold = currentText.getProperty(Property.BOLD_SIMULATION);
        UnitValue textSize = currentText.getProperty(Property.FONT_SIZE);

        assertTrue(isBold);
        assertEquals(10.0f, textSize.getValue());
    }  


}
