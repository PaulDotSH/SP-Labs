package ro.uvt.info.designpatternslab2023;

import java.util.List;

public class Paragraph extends Element {
    private String text;
    public Paragraph(String text) {
        this.text = text;
    }
    public Paragraph(Paragraph other){this.text = other.text;}

    @Override
    public void print(){
        System.out.println("Paragraph: " + text);
    }
    @Override
    public Element clone() {
        return new Paragraph(this);
    }


}
