package ro.uvt.info.designpatternslab2023;



import java.util.ArrayList;
import java.util.List;

public class Table extends Element {
    private String title;
    public Table(String title) {
        this.title = title;
    }
    public Table(Table other){
        this.title = other.title;
        this.elementList = new ArrayList<>(other.elementList);
    }

    @Override
    public void print(){
        System.out.println("Table with Title: " + title);
    }

    @Override
    public Element clone() {
        return new Table(this);
    }
}
