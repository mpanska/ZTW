package pl.edu.pwr.ztw.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.library.service.BookService;
import pl.edu.pwr.ztw.library.service.BorrowService;

@RestController
@CrossOrigin
@RequestMapping("/api/borrow")
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
