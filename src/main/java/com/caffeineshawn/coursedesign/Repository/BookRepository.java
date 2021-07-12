package com.caffeineshawn.coursedesign.Repository;

import com.caffeineshawn.coursedesign.Entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findBookByTitle(String title);

    @Query("SELECT book FROM book_table book WHERE book.title LIKE %:titleLike%")
    List<Book> findBookByTitleLike(String titleLike);
}
