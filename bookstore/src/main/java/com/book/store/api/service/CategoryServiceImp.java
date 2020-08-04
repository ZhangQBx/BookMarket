package com.book.store.api.service;

import com.book.store.api.domain.Category;
import com.book.store.api.handle.CategoryHandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp  implements CategoryService{

    //导入
    @Autowired(required = false)
    private CategoryHandle categoryHandle;

    @Override
    public List<Category> getAllCategory() {
        return categoryHandle.getAllCategory();
    }

}
