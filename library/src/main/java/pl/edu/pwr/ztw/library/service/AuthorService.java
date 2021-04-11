package pl.edu.pwr.ztw.library.service;
import pl.edu.pwr.ztw.library.entity.Author;
import java.util.List;

public interface AuthorService {
    public List<Author> findAll();

    public Author findById(Long id);

    public Author add(Author author);

    public Author update(Long id, Author author);

    public void deleteById(Long id);
}
