package ro.uvt.info.designpatternslab2023;

import java.util.ArrayList;
import java.util.List;

public class Section extends Element {
    protected String title;

    public Section(String title) {
        this.title = title;
        elementList = new ArrayList<>();
    }

    public Section(Section other){
        this.title = other.title;
        this.elementList = new ArrayList<>(other.elementList);
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
    public Element clone() {
        return new Section(this);
    }
}
