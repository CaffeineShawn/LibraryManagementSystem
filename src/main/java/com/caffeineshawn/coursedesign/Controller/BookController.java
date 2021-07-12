package com.caffeineshawn.coursedesign.Controller;


import com.caffeineshawn.coursedesign.Entity.Book;
import com.caffeineshawn.coursedesign.Exception.BookIdMismatchException;
import com.caffeineshawn.coursedesign.Exception.BookNotFoundException;
import com.caffeineshawn.coursedesign.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:8080")
public class BookController {
    @Autowired
    private BookRepository bookRepository;


    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }


    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findBookByTitle(bookTitle);
    }

    @GetMapping("/titles/{bookTitle}")
    public List findByTitleLike(@PathVariable String bookTitle) {
        return bookRepository.findBookByTitleLike("%" + bookTitle + "%");
    }


    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }


    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }



}
