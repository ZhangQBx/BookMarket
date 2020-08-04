package com.book.store.api.service;

import com.book.store.api.domain.Admin;
import com.book.store.api.handle.AdminHandle;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImp implements AdminService {

    @Autowired(required = false)
    private AdminHandle adminHandle;

    @Override
    public boolean addAdmin(Admin admin) {
        return adminHandle.insertBook(admin.getPhone(),admin.getPwd()) > 0;
    }

    @Override
    public Admin getAdmin(int phone) {
        return adminHandle.getAdmin(phone);
    }

}
