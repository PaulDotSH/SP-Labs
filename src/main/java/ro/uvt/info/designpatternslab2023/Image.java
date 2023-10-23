package ro.uvt.info.designpatternslab2023;


import java.util.List;

public class Image implements Element {
    private String imageName;

    public Image(String imageName) {
        this.imageName = imageName;
    }

    public void print(){
        System.out.println("Image with name: " + imageName);
    }

    @Override
    public void add(Element e) {
        throw new IllegalStateException("Cannot add an element");
    }

    @Override
    public void remove(Element e) {
        throw new IllegalStateException("Cannot remove an element");
    }

    @Override
    public Element get(int index) {
        throw new IllegalStateException("Cannot get an element");
    }
}
