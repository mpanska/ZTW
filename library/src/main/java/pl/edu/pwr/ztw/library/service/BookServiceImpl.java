package pl.edu.pwr.ztw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.library.entity.Author;
import pl.edu.pwr.ztw.library.entity.Book;
import pl.edu.pwr.ztw.library.repo.AuthorRepo;
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
                .orElseThrow(() -> new RuntimeException("Book not found: " + id));
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
            throw new RuntimeException("Book not found: " + id);
        return bookRepo.save(book);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepo.existsById(id))
            throw new RuntimeException("Book not found: " + id);
        bookRepo.deleteById(id);
    }

    public Book setAuthor(Book book, Author author) {
        Long id = book.getId();
        if (!bookRepo.existsById(id))
            throw new RuntimeException("Book not found: " + id);
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    AuthorService authorService;

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB() {
//
//        Book book1 = new Book("Potop", null, 936);
////        book1.setAuthor(authorService.findById(1L));
//        add(book1);
//
//        Book book2 = new Book("Wesele", null, 150);
////        book2.setAuthor(authorService.findById(2L));
//        add(book2);
//
//        Book book3 = new Book("Dziady", null, 292);
////        book3.setAuthor(authorService.findById(3L));
//        add(book3);
//
//    }
}
