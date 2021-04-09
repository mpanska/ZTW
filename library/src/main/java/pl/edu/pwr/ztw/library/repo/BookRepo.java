package pl.edu.pwr.ztw.library.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.ztw.library.entity.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {
}
