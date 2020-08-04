package com.book.store.api.handle;

import com.book.store.api.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserHandle {

    @Select("SELECT * FROM user where phone = #{phone}")
    User getUser(@Param("phone") String phone);

    @Insert("INSERT INTO user(phone,pwd) VALUES(#{phone},#{pwd})")
    int insertUser(@Param("phone") String phone, @Param("pwd") String pwd);

    @Insert("UPDATE user set name=#{name} where phone = #{phone}")
    int updateUser(@Param("name") String name, @Param("phone") String phone);

}
