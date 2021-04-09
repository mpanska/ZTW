package pl.edu.pwr.ztw.library.exception;

public class BookAlreadyReturnedException extends RuntimeException {
    public BookAlreadyReturnedException(Long id) {
        super("Book is already returned: " + id);
    }
}
