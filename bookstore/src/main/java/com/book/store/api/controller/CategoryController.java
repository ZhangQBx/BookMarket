package com.book.store.api.controller;

import com.book.store.api.common.ApiResult;
import com.book.store.api.domain.Category;
import com.book.store.api.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 对外暴露的接口
 */

@Api(value = "Restful API 服务")
@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查询所有记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResult getAllPeople() {
        List<Category> peoples = categoryService.getAllCategory();
        return ApiResult.success(peoples);
    }

}
