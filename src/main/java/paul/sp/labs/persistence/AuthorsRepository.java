package paul.sp.labs.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import paul.sp.labs.models.Author;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}
