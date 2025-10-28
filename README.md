# PDF Generator

The input is `input.txt` simply the provided input duplicated a number of times.

## Running

This project is built using Maven and runs on Java 21. It is packaged as a VSCode project so should be runnable through such, if not just run:

```bash
mvn compile exec:java
```

To run the program and you should see the `test.pdf` file should be created. If not within the examples folder there is a pre generated one I have created on my own environment.

I have also included a compiled `GearsetPDF-1.0-SNAPSHOT-jar-with-dependencies.jar` that can be run with:

```bash
java -jar GearsetPDF-1.0-SNAPSHOT-jar-with-dependencies.jar
```

```bash
mvn test
```

Should run the unit tests.

## Assumptions

I have made a number of assumptions to speed up development:

- Any instructions that are not defined such as `.small` will just be added to the document as normal text
- I have added a new `.paragraph` between each copy of your input as I am assuming `.large` will not add a new paragraph
- I have assumed that .large only sets the size, so large text can have both italics and bold
- I am assuming that when an indent is seen to start a new paragraph
- I am assuming that `.fill` will work outside of an indent block as this makes sense to me

To see my assumptions scroll to the bottom of the generated document and you will see some examples of what I have decided upon.