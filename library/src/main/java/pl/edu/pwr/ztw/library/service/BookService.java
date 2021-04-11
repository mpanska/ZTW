package pl.edu.pwr.ztw.library.service;

import pl.edu.pwr.ztw.library.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> findAll();

    public Book findById(Long id);

    public Book add(Book book);

    public Book update(Long id, Book book);

    public void deleteById(Long id);
}
