package pl.edu.pwr.ztw.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.library.entity.Author;
import pl.edu.pwr.ztw.library.service.AuthorService;

@RestController
@CrossOrigin
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<?> getAuthors() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.add(author), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return new ResponseEntity<>(authorService.update(id, author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
