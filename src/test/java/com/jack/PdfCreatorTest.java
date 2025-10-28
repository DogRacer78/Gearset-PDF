package com.jack;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class PdfCreatorTest {
    private PdfCreator pdfCreator;
    private DocumentBuilder documentBuilderMock;

    @Nested
    @DisplayName("Large Text Tests")
    class LargeTextTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".large");
            commands.add("This is some large text");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        void shouldCallCreateLargeText() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setLargeFontSize();
            verify(documentBuilderMock).addText("This is some large text ");
        }

    }

    @Nested
    @DisplayName("Italic Test")
    class ItalicTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".italic");
            commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to set italic text")
        void italicTestValid() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setItalic();
            verify(documentBuilderMock).addText("test ");
        }

    }

    @Nested
    @DisplayName("Bold Test")
    class BoldTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".bold");
            commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to set bold text")
        void boldTextValid() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setBold();
            verify(documentBuilderMock).addText("test ");
        }

    }

    @Nested
    @DisplayName("Normal Test")
    class NormalTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".normal");
            commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to set normal text size")
        void normalTextValid() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setNormalFontSize();
            verify(documentBuilderMock).addText("test ");
        }

    }

    @Nested
    @DisplayName("Indent Test")
    class IndentTest {

        ArrayList<String> commands;

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            this.commands = new ArrayList<String>();
            this.commands.add(".indent +2");
            this.commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to increase the indentation")
        void indentTextTest() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setIndent(2.0f);
            verify(documentBuilderMock).addText("test ");
        }

        @Test
        @DisplayName("Should use 0 as the indent when indent is invalid")
        void indentExceptionTest() {
            this.commands.set(0, ".indent abc");

            pdfCreator.createDocument();

            verify(documentBuilderMock).setIndent(0);
        }

    }

    @Nested
    @DisplayName("Fill Test")
    class FillTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".fill");
            commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to set fill on")
        void fillTest() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setFill();
            verify(documentBuilderMock).addText("test ");
        }

    }

    @Nested
    @DisplayName("nofill Test")
    class NoFillTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".nofill");
            commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to set fill off")
        void noFillTest() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setNoFill();
            verify(documentBuilderMock).addText("test ");
        }

    }

    @Nested
    @DisplayName("regular Test")
    class RegularTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".regular");
            commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to set regular font size")
        void noFillTest() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).setRegularFont();
            verify(documentBuilderMock).addText("test ");
        }

    }

    @Nested
    @DisplayName("Paragraph Test")
    class ParagraphTest {

        @BeforeEach
        void beforeAll() throws IOException {
            documentBuilderMock = mock();

            ArrayList<String> commands = new ArrayList<String>();
            commands.add(".paragraph");
            commands.add("test");

            pdfCreator = new PdfCreator(commands, documentBuilderMock);
        }

        @Test
        @DisplayName("Should call method to create new paragraph")
        void noFillTest() {
            pdfCreator.createDocument();

            verify(documentBuilderMock).addParagraph();
            verify(documentBuilderMock).addText("test ");
        }

    }

}
