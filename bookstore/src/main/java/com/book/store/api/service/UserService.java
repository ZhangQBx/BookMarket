package com.book.store.api.service;

import com.book.store.api.domain.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    boolean addUser(String phone,String pwd);

    User getUser(String phone);

    boolean updateUser(User user);
}
