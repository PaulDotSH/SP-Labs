package paul.sp.labs.services;

import paul.sp.labs.models.Book;

import java.io.IOException;

public interface Observer {
    void update(Book book) throws IOException;
}
