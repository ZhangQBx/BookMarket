package com.book.store.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.book.store.api.common.ApiResult;

import com.book.store.api.domain.User;
import com.book.store.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "Restful API 服务")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult register(String phone, String pwd) {

        User user = userService.getUser(phone);

        if ( user!= null ) {
            return ApiResult.failure("账号已存在");
        }else {
            boolean result = userService.addUser(phone,pwd);
            if (result) {
                return ApiResult.success("新增一条记录成功！");
            } else {
                return ApiResult.failure("新增失败！");
            }
        }
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult login(String phone, String pwd) {

        User user = userService.getUser(phone);

        if ( user == null ) {
            return ApiResult.failure("账号不存在");
        }else {

            if (user.getPwd().equals(pwd)) {
                return ApiResult.success("登录成功！");
            } else {
                return ApiResult.failure("密码错误！");
            }
        }
    }

}
