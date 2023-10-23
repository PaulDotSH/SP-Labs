package ro.uvt.info.designpatternslab2023;


import java.util.ArrayList;
import java.util.List;

public class SubChapter {
    private String name;
    private List<Element> elementList;

    public SubChapter(String name) {
        this.name = name;
        elementList = new ArrayList<>();
    }

    public SubChapter(SubChapter other){
        this.name = other.name;
        this.elementList = new ArrayList<>(other.elementList);
    }

    public void print() {
        System.out.printf("Subchapter: %s%n", name);
        for (Element element :
                elementList) {
            element.print();
        }
    }

    public void createNewParagraph(String paragraphName) {
        Element newParagraph = new Paragraph(paragraphName);
        elementList.add(newParagraph);
    }

    public void createNewImage(String imageName) {
        Element newImage = new Image(imageName);
        elementList.add(newImage);
    }

    public void createNewTable(String tableName) {
        Element newTable = new Table(tableName);
        elementList.add(newTable);
    }
}
