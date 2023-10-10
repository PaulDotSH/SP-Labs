package com.example.splabs;

import java.util.ArrayList;

public class Book {
    Book(String title) {
        this.title = title;
    }
    public String title;
    public TableOfContents contents;
    public ArrayList<Author> authors;
    public ArrayList<Chapter> chapters;
    public void print() {

    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public int createChapter(String s) {
        Chapter c = new Chapter(s);
        chapters.add(c);
        return chapters.size() - 1;
    }

    public Chapter getChapter(int index) {
        return chapters.get(index);
    }
}
