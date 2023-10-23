package ro.uvt.info.designpatternslab2023;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
    protected String title;
    protected List<Element> elementList;

    public Section(String title) {
        this.title = title;
        elementList = new ArrayList<>();
    }

    @Override
    public void print() {
        System.out.println(title);
        for (Element element :
                elementList) {
            element.print();
        }
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
}
