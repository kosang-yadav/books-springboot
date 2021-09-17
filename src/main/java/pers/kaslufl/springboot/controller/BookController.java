package pers.kaslufl.springboot.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import pers.kaslufl.springboot.model.entity.Book;
import pers.kaslufl.springboot.model.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookRepository bookRepository;

    public BookController(JdbcTemplate jdbcTemplate) {
        this.bookRepository = new BookRepository(jdbcTemplate);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @GetMapping("/{id}")
    public Book search(@PathVariable int id) {
        return bookRepository.search(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) throws Exception{
        return bookRepository.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@RequestBody Book book, @PathVariable int id) {
        book.setId(id);
        return bookRepository.update(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        bookRepository.delete(id);
    }
}
