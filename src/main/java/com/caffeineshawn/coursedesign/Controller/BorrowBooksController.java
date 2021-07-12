package com.caffeineshawn.coursedesign.Controller;


import com.caffeineshawn.coursedesign.Entity.Book;
import com.caffeineshawn.coursedesign.Entity.BookUser;
import com.caffeineshawn.coursedesign.Repository.BookRepository;
import com.caffeineshawn.coursedesign.Repository.BookUserRepository;
import com.caffeineshawn.coursedesign.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books/borrow")
@CrossOrigin(origins = "http://localhost:8080")
public class BorrowBooksController {
    @Autowired
    private BookUserRepository bookUserRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Optional<BookUser> findOne(@PathVariable Long id) {
        return bookUserRepository.findById(id);
    }


    @GetMapping("/user/{userId}")
    public List userRecord(@PathVariable Long userId) {
         return bookUserRepository.findBookUserByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookUser newBorrowRecord(@RequestBody BookUser bookUser) {
        // 前端传来的用户id和书id形成借书记录，数量默认为1
        Long userId = bookUser.getUserId();
        Long bookId = bookUser.getBookId();
        if (bookUserRepository.findByUserIdAndBookId(userId,bookId).isPresent() ) {
            return null;

        } else {
            Book book = bookRepository.findById(bookUser.getBookId()).get();
            int quantity = book.getQuantity();
            if (quantity <= 0) {
                return null;
            }
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
            return bookUserRepository.save(bookUser);
        }
    }



    @DeleteMapping("/{userId}/{bookId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long userId, @PathVariable Long bookId) {
        // 简单还个书

        BookUser record = bookUserRepository.findByUserIdAndBookId(userId, bookId).get();
            Book book = bookRepository.findById(record.getBookId()).get();
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
            bookUserRepository.deleteByUserIdAndAndBookId(userId, bookId);

    }




}
