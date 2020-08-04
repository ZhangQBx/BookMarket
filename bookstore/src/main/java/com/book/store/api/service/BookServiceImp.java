package com.book.store.api.service;

import com.book.store.api.domain.Book;
import com.book.store.api.handle.BookHandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired(required = false)
    private BookHandle bookHandle;

    @Override
    public List<Book> getBooks(String category,int page,int pageSize) {
        int count = (page - 1) * pageSize;
        return bookHandle.getBooks(category,count,pageSize);
    }

    @Override
    public List<Book> getAllBooks(int page,int pageSize) {
        int count = (page - 1) * pageSize;
        return bookHandle.getAllBooks(count,pageSize);
    }

    @Override
    public List<Book> searchBooks(String words) {
        String keyWord = '%' + words + '%';
        return bookHandle.searchBooks(keyWord);
    }

    @Override
    public int getBooksCount(String category) {
        return bookHandle.getBooksCount(category);
    }

    @Override
    public int getAllBooksCount() {
        return bookHandle.getAllBooksCount();
    }

    @Override
    public Book getBook(int id) {
        return bookHandle.getBook(id);
    }

    @Override
    public boolean addBook(Book book) {
        return bookHandle.insertBook(book.getCategory(),book.getTitle(),book.getTags(),book.getSub1(),book.getSub2(),book.getImg(),
                book.getPrice(),book.getDiscount(),book.isHot(),book.isNew(),book.isRacking(),book.getRack_start(),book.getRack_end(),book.getCreat_time())>0;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookHandle.updateBook(book.getTags(),book.getSub1(),book.getSub2(),book.getImg(),
                book.getPrice(),book.getDiscount(),book.isHot(),book.isNew(),book.isRacking(),book.getRack_start(),book.getRack_end(),book.getId())>0;
    }

    @Override
    public boolean deleteBook(int id) {
        return bookHandle.deleteBook(id)>0;
    }

}
