package paul.sp.labs.services;

import org.springframework.stereotype.Component;
import paul.sp.labs.models.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AllBooksSubject {
    private final List<Observer> observerCollection;

    public AllBooksSubject() {
        this.observerCollection = new ArrayList<>();
    }

    public void attach(Observer observer) {
        observerCollection.add(observer);
    }

    public void detach(Observer observer) {
        observerCollection.remove(observer);
    }

    public void notifyObservers(Book book) {
        for (Observer observer : observerCollection) {
            try {
                observer.update(book);
            } catch (IOException e) {
                detach(observer);
            }
        }
    }


    public void add(Book book) {
        notifyObservers(book);
    }
}
