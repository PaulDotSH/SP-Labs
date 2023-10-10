package com.example.splabs;

import java.util.ArrayList;

public class Chapter {
    Chapter(String s) {
        name = s;
    }
    public String name;
    public ArrayList<Subchapter> subchapters;
    public void print() {

    }

    public int createSubChapter(String s) {
        Subchapter sbch = new Subchapter(s);
        subchapters.add(sbch);
        return subchapters.size() - 1;
    }

    public Subchapter getSubChapter(int index) {
        return subchapters.get(index);
    }
}
