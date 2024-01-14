package paul.sp.labs.persistence;

import org.springframework.stereotype.Repository;
import paul.sp.labs.models.Book;

import java.util.List;

@Repository
public class BooksCrudRepositoryAdapter implements BooksCrudRepository {

    private final BookJpaRepository bookJpaRepository;

    public BooksCrudRepositoryAdapter(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookJpaRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookJpaRepository.deleteById(id);
    }

    @Override
    public Book update(Long id, Book updatingBook) {
        return bookJpaRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatingBook.getTitle());
                    existingBook.setAuthorList(updatingBook.getAuthorList());
                    existingBook.setElementList(updatingBook.getElementList());
                    return bookJpaRepository.save(existingBook);
                })
                .orElse(null);
    }

}