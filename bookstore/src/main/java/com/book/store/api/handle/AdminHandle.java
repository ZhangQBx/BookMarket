package com.book.store.api.handle;

import com.book.store.api.domain.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminHandle {

    @Select("SELECT * FROM admin where phone = #{phone}")
    Admin getAdmin(@Param("phone") int phone);

    @Insert("INSERT INTO admin(phone,pwd) VALUES(#{phone},#{pwd})")
    int insertBook(@Param("phone") int phone, @Param("pwd") String pwd);

}
