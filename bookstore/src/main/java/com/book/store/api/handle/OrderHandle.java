package com.book.store.api.handle;

import com.book.store.api.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderHandle {

    @Select("SELECT * FROM book_order where phone = #{phone} order by id limit #{count},#{pageSize}")
    List<Order> getOrders(String phone, int count, int pageSize);

    @Select("SELECT * FROM book_order where phone = #{phone} And status = #{status}")
    Order getOrder(String phone, int status);

    @Insert("INSERT INTO book_order(phone,book_ids,book_count,status,create_time,is_delete) " +
            "VALUES(#{phone},#{book_ids},#{book_count},#{status},#{create_time},#{is_delete})")
    int addOrder(@Param("phone") String phone, @Param("book_ids") String book_ids, @Param("book_count") String book_count,
                   @Param("status") int status, @Param("create_time") int create_time, @Param("is_delete") boolean is_delete);


    @Update("UPDATE book_order set book_ids=#{book_ids},book_count=#{book_count},status=#{status}," +
            "is_delete=#{is_delete} where phone = #{phone}")
    int updateOrder(@Param("book_ids") String book_ids, @Param("book_count") String book_count,
                   @Param("status") int status, @Param("is_delete") boolean is_delete, @Param("phone") String phone);

}
