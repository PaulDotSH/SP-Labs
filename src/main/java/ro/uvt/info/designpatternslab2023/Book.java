package ro.uvt.info.designpatternslab2023;

import java.util.ArrayList;
import java.util.List;

public class Book extends Section {
    private List<Author> authorList;
    public Book(String title) {
        super(title);
        authorList = new ArrayList<>();
    }

    public Book(Book other){
        super(other.title);
        this.authorList = new ArrayList<>(other.authorList);
    }


    @Override
    public void print(){
        System.out.println("Book: " + title );
        System.out.println();

        System.out.println("Authors: ");
        for (Author author :
                authorList) {
            author.print();
        }
        System.out.println();

        for (Element element :
                elementList) {
            element.print();
        }


    }

    public void addAuthor(Author author) {
        this.authorList.add(new Author(author));
    }

}
