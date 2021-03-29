package pl.edu.pwr.ztw.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.pwr.ztw.library.exception.AuthorNotFoundException;
import pl.edu.pwr.ztw.library.exception.BookAlreadyBorrowedException;
import pl.edu.pwr.ztw.library.exception.BookAlreadyReturnedException;
import pl.edu.pwr.ztw.library.exception.BookNotFoundException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> bookNotFoundHandler(BookNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<?> authorNotFoundHandler(AuthorNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyBorrowedException.class)
    public ResponseEntity<?> bookAlreadyBorrowedHandler(BookAlreadyBorrowedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookAlreadyReturnedException.class)
    public ResponseEntity<?> bookAlreadyReturnedHandler(BookAlreadyReturnedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
