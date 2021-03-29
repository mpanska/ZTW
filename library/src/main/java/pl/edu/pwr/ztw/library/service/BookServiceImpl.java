package pl.edu.pwr.ztw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.library.entity.Author;
import pl.edu.pwr.ztw.library.entity.Book;
import pl.edu.pwr.ztw.library.exception.BookNotFoundException;
import pl.edu.pwr.ztw.library.repo.BookRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        bookRepo.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book findById(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book add(Book book) {
        book.setId(0L);
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        Long id = book.getId();
        if (!bookRepo.existsById(id))
            throw new BookNotFoundException(id);
        return bookRepo.save(book);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepo.existsById(id))
            throw new BookNotFoundException(id);
        bookRepo.deleteById(id);
    }
}
