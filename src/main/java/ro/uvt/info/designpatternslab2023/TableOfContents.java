package ro.uvt.info.designpatternslab2023;

import java.util.ArrayList;

public class TableOfContents extends Element {
    public TableOfContents(){
        elementList = new ArrayList<>();
    }

    public TableOfContents(TableOfContents other){
        elementList = new ArrayList<>(other.elementList);
    }

    @Override
    public void print(){}

    @Override
    public Element clone() {
        return new TableOfContents(this);
    }
}
