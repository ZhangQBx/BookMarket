package com.book.store.api.controller;

import com.book.store.api.common.ApiResult;
import com.book.store.api.domain.Book;
import com.book.store.api.domain.Order;
import com.book.store.api.service.BookService;
import com.book.store.api.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(value = "Restful API 服务")
@RestController
@RequestMapping("/v1/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询某一些记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResult getOrder(String phone,int page,int pageSize) {

        List<Order> orders = orderService.getOrders(phone,page,pageSize);

        List<Order> new_orders = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            JSONArray json = JSONArray.parseArray(order.getBook_ids());

            List<Book> books = new ArrayList<>();
            for ( int j = 0; j < json.size(); j++) {
                int book_id = (int)json.get(j);
                Book book = bookService.getBook(book_id);
                books.add(book);
            }
            order.setBooks(books);
            new_orders.add(order);
        }

        if (null != orders) {
            return ApiResult.success(new_orders);
        } else {
            return ApiResult.failure("数据不存在");
        }
    }

    @ApiOperation(value = "新增某一条记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult addOrder(@RequestBody Order order) {

        Order order_o = orderService.getOrder(order.getPhone(),0);
        if (order_o == null) {
            boolean result = orderService.addOrder(order);
            if (result) {
                return ApiResult.success("新增一条记录成功！");
            } else {
                return ApiResult.failure("新增失败！");
            }
        }else {
            boolean result = orderService.updateOrder(order);
            if (result) {
                return ApiResult.success("更新一条记录成功！");
            } else {
                return ApiResult.failure("更新失败！");
            }
        }
    }

    @ApiOperation(value = "更新某一条记录")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResult updateBook(@RequestBody Order order) {
        boolean result = orderService.updateOrder(order);
        if (result) {
            return ApiResult.success("更新成功！");
        } else {
            return ApiResult.failure("更新失败！");
        }
    }

}
