package com.book.store.api.domain;

public class Category {
    private int id;
    private String categoryId;
    private String title;

    public Category() {
    }

    public Category(int id ,String categoryId, String title) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
