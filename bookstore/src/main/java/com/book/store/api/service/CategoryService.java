package com.book.store.api.service;

import com.book.store.api.domain.Category;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {

    //获取所有的类别数据
    List<Category> getAllCategory();

}
