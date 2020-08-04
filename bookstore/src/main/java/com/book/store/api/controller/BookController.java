package com.book.store.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.book.store.api.common.ApiResult;
import com.book.store.api.domain.Book;
import com.book.store.api.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Restful API 服务")
@RestController
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询某一些记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResult getBooks(String category,int page,int pageSize) {

        List<Book> books = bookService.getBooks(category,page,pageSize);
        int totleCount = bookService.getBooksCount(category);
        String s ="{totleCount:" + String.valueOf(totleCount) + "}";
        JSONObject jsonobject = JSONObject.parseObject(s);

        if (null != books) {
            return ApiResult.success(books,jsonobject);
        } else {
            return ApiResult.failure("数据不存在");
        }
    }

    @ApiOperation(value = "查询某一些记录")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ApiResult getAllBooks(int page,int pageSize) {

        List<Book> books = bookService.getAllBooks(page,pageSize);
        int totleCount = bookService.getAllBooksCount();
        String s ="{totleCount:" + String.valueOf(totleCount) + "}";
        JSONObject jsonobject = JSONObject.parseObject(s);

        if (null != books) {
            return ApiResult.success(books,jsonobject);
        } else {
            return ApiResult.failure("数据不存在");
        }
    }

    @ApiOperation(value = "查询某一些记录")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ApiResult searchBooks(String keyWords) {

        List<Book> books = bookService.searchBooks(keyWords);

        if (null != books) {
            return ApiResult.success(books);
        } else {
            return ApiResult.failure("数据不存在");
        }
    }

    @ApiOperation(value = "查询某一条记录")
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ApiResult getBook(int id) {

        Book book = bookService.getBook(id);
        if (null != book) {
            return ApiResult.success(book);
        } else {
            return ApiResult.failure("数据不存在");
        }
    }

    @ApiOperation(value = "新增某一条记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult addBook(@RequestBody Book book) {
        boolean result = bookService.addBook(book);
        if (result) {
            return ApiResult.success("新增一条记录成功！");
        } else {
            return ApiResult.failure("新增失败！");
        }
    }

    @ApiOperation(value = "更新某一条记录")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResult updateBook(@RequestBody Book book) {
        boolean result = bookService.updateBook(book);
        if (result) {
            return ApiResult.success("更新成功！");
        } else {
            return ApiResult.failure("更新失败！");
        }
    }

    @ApiOperation(value = "删除某一条记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResult deleteBook(@RequestBody Book book) {
        boolean result = bookService.deleteBook(book.getId());
        if (result) {
            return ApiResult.success("删除成功！");
        } else {
            return ApiResult.failure("删除失败！");
        }
    }

}
