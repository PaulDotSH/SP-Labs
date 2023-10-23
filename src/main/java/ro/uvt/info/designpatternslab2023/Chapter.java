package ro.uvt.info.designpatternslab2023;

import java.util.ArrayList;
import java.util.List;

public class Chapter {
    private String name;
    private List<SubChapter> subChapterList;

    public Chapter(String name) {
        this.name = name;
        this.subChapterList = new ArrayList<SubChapter>();
    }

    public void print(){
        for (SubChapter subChapter:
                subChapterList) {
            subChapter.print();
        }

    }

    public int createSubChapter(String subChapterName) {
        SubChapter newSubChapter = new SubChapter(subChapterName);
        subChapterList.add(newSubChapter);
        return subChapterList.size() - 1;
    }

    public SubChapter GetSubChapter(int index) {
        return subChapterList.get(index);
    }
}
