package pl.edu.pwr.ztw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.library.entity.Book;
import pl.edu.pwr.ztw.library.exception.BookAlreadyBorrowedException;
import pl.edu.pwr.ztw.library.exception.BookAlreadyReturnedException;

@Service
public class BorrowServiceImpl implements BorrowService {

    private BookService bookService;

    @Autowired
    public BorrowServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void borrowBook(Long id) {
        Book book = bookService.findById(id);
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            bookService.update(book);
        }
        else
            throw new BookAlreadyBorrowedException(id);
    }

    @Override
    public void returnBook(Long id) {
        Book book = bookService.findById(id);
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            bookService.update(book);
        }
        else
            throw new BookAlreadyReturnedException(id);
    }
}
