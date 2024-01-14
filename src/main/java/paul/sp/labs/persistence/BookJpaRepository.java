package paul.sp.labs.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paul.sp.labs.models.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
}