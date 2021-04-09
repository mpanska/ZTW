package pl.edu.pwr.ztw.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.edu.pwr.ztw.library.entity.Author;
import pl.edu.pwr.ztw.library.entity.Book;
import pl.edu.pwr.ztw.library.service.AuthorService;
import pl.edu.pwr.ztw.library.service.BookService;

@Component
public class DataLoader implements ApplicationRunner {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public DataLoader(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public void run(ApplicationArguments args) {
        Author a1 = new Author("Henryk", "Sienkiewicz");
        Author a2 = new Author("Stanislaw", "Reymont");
        Author a3 = new Author("Adam", "Mickiewicz");
        authorService.add(a1);
        authorService.add(a2);
        authorService.add(a3);

        Book book1 = new Book("Potop", null, 936);
        book1.setAuthor(authorService.findById(1L));
        Book book2 = new Book("Wesele", null, 150);
        book2.setAuthor(authorService.findById(2L));
        Book book3 = new Book("Dziady", null, 292);
        book3.setAuthor(authorService.findById(3L));
        bookService.add(book1);
        bookService.add(book2);
        bookService.add(book3);
    }
}
