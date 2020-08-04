package com.book.store.api.handle;

import com.book.store.api.domain.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryHandle {

    /***
     * 查询数据表中的所有记录
     * 对应SQL：select * from people
     * @return
     */
    @Select("SELECT * FROM cateGory")
    List<Category> getAllCategory();

}
