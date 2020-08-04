package com.book.store.api.service;

import com.book.store.api.domain.Admin;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {

    boolean addAdmin(Admin admin);

    Admin getAdmin(int phone);

}
