package com.caffeineshawn.coursedesign.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "borrow_book_table")
public class BookUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long bookId;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {

        this.id = id;
    }


    public Long getId() {
        return id;
    }


}
