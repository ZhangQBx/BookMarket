package com.book.store.api.domain;

import java.util.List;

public class Order {
    private int id;
    private String phone;
    private String book_ids;
    private String book_count;
    private int status;
    private int create_time;
    private boolean is_delete;
    private List<Book> books;

    public Order() {

    }

    public Order(int id,String phone,String book_ids,String book_count,int status, int create_time,boolean is_delete) {
        this.id = id;
        this.phone = phone;
        this.book_ids = book_ids;
        this.book_count = book_count;
        this.status = status;
        this.create_time = create_time;
        this.is_delete = is_delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_ids() {
        return book_ids;
    }

    public void setBook_ids(String book_ids) {
        this.book_ids = book_ids;
    }

    public String getBook_count() {
        return book_count;
    }

    public void  setBook_count(String book_count) {this.book_count = book_count;}

    public int getStatus() { return status; }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCreate_time() { return create_time; }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public boolean isIs_delete () {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks () {return books;}
}
