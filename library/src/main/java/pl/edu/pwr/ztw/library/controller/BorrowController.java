package pl.edu.pwr.ztw.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.ztw.library.service.BookService;
import pl.edu.pwr.ztw.library.service.BorrowService;

@RestController
@RequestMapping("/api")
public class BorrowController {

    private BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PatchMapping("/borrow/{id}")
    public ResponseEntity<?> borrowBook(@PathVariable Long id) {
        borrowService.borrowBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        borrowService.returnBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
