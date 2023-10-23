package ro.uvt.info.designpatternslab2023;



import java.util.List;

public class Table implements Element {
    private String title;
    private List<Element> elementList;

    public Table(String title) {
        this.title = title;
    }

    public void print(){
        System.out.println("Table with Title: " + title);
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
