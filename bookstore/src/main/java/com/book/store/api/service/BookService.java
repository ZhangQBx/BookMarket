package com.book.store.api.service;

import com.book.store.api.domain.Book;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface BookService {

    List<Book> getBooks(String category, int page ,int pageSize);

    List<Book> getAllBooks(int page,int pageSize);

    int getAllBooksCount();

    Book getBook(int id);

    boolean addBook(Book book);

    boolean updateBook(Book book);

    List<Book> searchBooks(String words);

    boolean deleteBook(int id);

    int getBooksCount(String category);
}
