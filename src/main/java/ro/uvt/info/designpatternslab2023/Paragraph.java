package ro.uvt.info.designpatternslab2023;

import java.util.List;

public class Paragraph implements Element {
    private String text;
    private List<Element> elementList;
    public void print(){
        System.out.println("Paragraph: " + text);
    }

    @Override
    public void add(Element e) {
        elementList.add(e);
    }

    @Override
    public void remove(Element e) {
        elementList.remove(e);
    }

    @Override
    public Element get(int index) {
        return elementList.get(index);
    }

    public Paragraph(String text) {
        this.text = text;
    }
}
