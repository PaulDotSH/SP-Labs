package paul.sp.labs.persistence;


import java.util.List;

public interface CrudRepository<T, TId> {
    List<T> getAll();

    T findById(TId id);

    T save(T other);

    void deleteById(TId id);

    T update(Long id, T updatingBook);
}
