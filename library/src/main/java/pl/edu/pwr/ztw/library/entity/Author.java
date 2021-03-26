package pl.edu.pwr.ztw.library.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name="AUTHOR")
public class Author {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String authorName;
    private String authorLastName;

    @OneToMany(mappedBy="author")
    private List<Book> books;

    public Author(String authorName, String authorLastName) {
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.books = new ArrayList<>();
    }

    public Author() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBooks(Book book) {
        this.books.add(book);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}


