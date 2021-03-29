package pl.edu.pwr.ztw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.library.entity.Author;
import pl.edu.pwr.ztw.library.repo.AuthorRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorRepo authorRepo;
    private BookService bs;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }


    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        authorRepo.findAll().forEach(authors::add);
        return authors;
    }

    @Override
    public Author findById(Long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found: " + id));
    }

    @Override
    public Author add(Author author) {
        author.setId(0L);
        return authorRepo.save(author);
    }

    @Override
    public Author update(Author author) {
        Long id = author.getId();
        if (!authorRepo.existsById(id))
            throw new RuntimeException("Author not found: " + id);
        return authorRepo.save(author);
    }

    @Override
    public void deleteById(Long id) {
        if (!authorRepo.existsById(id))
            throw new RuntimeException("Author not found: " + id);
        authorRepo.deleteById(id);
    }
}
