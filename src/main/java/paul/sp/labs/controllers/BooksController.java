package paul.sp.labs.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paul.sp.labs.commands.*;
import paul.sp.labs.persistence.CrudRepository;
import paul.sp.labs.models.Book;
import paul.sp.labs.models.Pair;
import paul.sp.labs.services.AllBooksSubject;
import paul.sp.labs.services.BookStatistics;
import paul.sp.labs.services.CommandExecutor;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final Command<List<Book>, Void> getAll;
    private final Command<Book, Long> getOne;
    private final Command<Book, Book> addOne;
    private final Command<Book, Pair<Long, Book>> updateOne;
    private final Command<Void, Long> deleteOne;
    private final CommandExecutor commandExecutor;
    private final AllBooksSubject allBooksSubject;

    public BooksController(CrudRepository<Book, Long> bookRepository,
                           CommandExecutor commandExecutor, AllBooksSubject allBooksSubject
    ) {

        // Nu trebuie pus type-ul aici
        getAll = new GetAllCommand<>(bookRepository);
        getOne = new FindOneCommand<>(bookRepository);
        addOne = new AddOneCommand<>(bookRepository);
        updateOne = new UpdateOneCommand<>(bookRepository);
        deleteOne = new DeleteOneCommand<>(bookRepository);
        this.commandExecutor = commandExecutor;
        this.allBooksSubject = allBooksSubject;
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> printStatistics() {
        var books = getAll.execute();

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var book = books.iterator().next();
        BookStatistics stats = calculateStatistics(book);

        return new ResponseEntity<>(stats.getStatistics(), HttpStatus.OK);
    }

    private BookStatistics calculateStatistics(Book book) {
        BookStatistics stats = new BookStatistics();
        book.accept(stats);
        return stats;
    }


    @GetMapping("")
    public ResponseEntity<?> getBooks() {
        Iterable<Book> books = commandExecutor.execute(getAll);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        try {
            getOne.setCommandContext(id);
            Book book = commandExecutor.execute(getOne);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public String addBook(@RequestBody Book book) {
        addOne.setCommandContext(book);
        Book insertedBook = commandExecutor.execute(addOne);
        allBooksSubject.add(book);

        return String.format("Book saved [%d] %s", insertedBook.getId(), insertedBook.getTitle());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> putBook(@PathVariable Long id, @RequestBody Book book) {
        Pair<Long, Book> pair = new Pair<>(id, book);
        updateOne.setCommandContext(pair);
        Book updatedBook = commandExecutor.execute(updateOne);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        deleteOne.setCommandContext(id);
        commandExecutor.execute(deleteOne);

        return ResponseEntity.ok("Removed");
    }


    @GetMapping("/async/{opId}")
    public ResponseEntity<?> getAsyncResult(@PathVariable String opId) {
        return new ResponseEntity<>(commandExecutor.getAsyncResult(opId), HttpStatus.OK);
    }


}