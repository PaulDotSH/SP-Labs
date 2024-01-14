package paul.sp.labs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import paul.sp.labs.models.*;
import paul.sp.labs.services.BookSaveVisitor;

// De la lab-urile cu spring am fisier separat de rulat
@SpringBootApplication
public class DesignPatterns {

    public static void main(String[] args) {
        createContents();
    }

    public static void createContents() {
        Book b = new Book("Eminescu - Poezii");
        Section chapter1 = new Section("Capitolul 1 - Poezii Vechi");
        chapter1.add(new Paragraph("Paragraf 1"));
        chapter1.add(new Paragraph("Paragraf 2"));
        Section subchapter = new Section("Subcapitol 1.1");
        subchapter.add(new ImageProxy("Imagine cu pisica"));
        subchapter.add(new Image("Imagine cu copac"));

        chapter1.add(subchapter);
        chapter1.add(new Paragraph("Foo"));
        chapter1.add(new Table("Table"));


        Section chapter2 = new Section("Capitolul 2 - Poezii noi");
        chapter2.add(new Paragraph("Paragraf 3"));

        b.add(chapter1);
        b.add(chapter2);

        BookSaveVisitor saveVisitor = new BookSaveVisitor();
        b.accept(saveVisitor);
        System.out.println(saveVisitor.getJson());
    }


}
