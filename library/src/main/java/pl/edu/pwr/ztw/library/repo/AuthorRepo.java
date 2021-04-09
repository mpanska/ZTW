package pl.edu.pwr.ztw.library.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.ztw.library.entity.Author;

@Repository
public interface AuthorRepo  extends CrudRepository<Author, Long> {
}


