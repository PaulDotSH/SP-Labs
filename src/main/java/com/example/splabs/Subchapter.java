package com.example.splabs;

import java.util.ArrayList;

public class Subchapter {
    Subchapter(String s) {
        name = s;
    }
    public ArrayList<Image> images;

    public ArrayList<Paragraph> paragraphs;

    public ArrayList<Table> tables;

    public String name;
    public void print() {

    }

    public void createNewParagraph(String s) {
        Paragraph ph = new Paragraph(s);
        paragraphs.add(ph);
    }

    public void createNewImage(String s) {
        Image img = new Image(s);
        images.add(img);
    }

    public void createNewTable(String s) {
        Table t = new Table(s);
        tables.add(t);
    }
}
