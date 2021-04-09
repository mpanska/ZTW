package pl.edu.pwr.ztw.library.service;

public interface BorrowService {

    public void borrowBook(Long id);

    public void returnBook(Long id);
}
