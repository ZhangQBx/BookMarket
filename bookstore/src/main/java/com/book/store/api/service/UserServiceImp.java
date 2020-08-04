package com.book.store.api.service;

import com.book.store.api.handle.UserHandle;
import com.book.store.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired(required = false)
    private UserHandle userHandle;

    @Override
    public boolean addUser(String phone,String pwd) {
        return userHandle.insertUser(phone,pwd) > 0;
    }

    @Override
    public User getUser(String phone) {
        return userHandle.getUser(phone);
    }

    @Override
    public boolean updateUser(User user) {
        return userHandle.updateUser(user.getName(),user.getPhone()) > 0;
    }
}
