package paul.sp.labs.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Book extends Section implements Visitee {
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authorList;

    public Book() {
        super(""); // Ce imi place java
        authorList = new ArrayList<>();
    }

    public Book(String title) {
        super(title);
        authorList = new ArrayList<>();
    }

    public Book(Book other) {
        super(other.title);
        this.authorList = new ArrayList<>(other.authorList);
    }

    public void addAuthor(Author author) {
        this.authorList.add(new Author(author));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBook(this);
    }

}
