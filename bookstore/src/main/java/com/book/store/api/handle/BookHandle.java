package com.book.store.api.handle;

import com.book.store.api.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookHandle {

    @Select("SELECT * FROM book where category = #{category}  order by id limit #{count},#{pageSize}")
    List<Book> getBooks(@Param("category") String category, int count, int pageSize);

    @Select("SELECT * FROM book order by id limit #{count},#{pageSize}")
    List<Book> getAllBooks(int count,int pageSize);

    @Select("SELECT count(*) FROM book")
    int getAllBooksCount();

    @Select("SELECT * FROM book where title like #{words}")
    List<Book> searchBooks(@Param("words") String words);

    @Select("SELECT count(*) FROM book where category = #{category}")
    int getBooksCount(@Param("category") String category);

    @Select("SELECT * FROM book where id = #{id}")
    Book getBook(int id);

    @Insert("INSERT INTO book(category,title,tags,sub1,sub2,img,price,discount,hot,new,racking,rack_start,rack_end,creat_time)" +
            "VALUES(#{category},#{title},#{tags},#{sub1},#{sub2},#{img},#{price},#{discount},#{hot},#{isNew},#{racking},#{rack_start},#{rack_end},#{creat_time})")
    int insertBook(@Param("category") String category, @Param("title") String title, @Param("tags") String tags, @Param("sub1") String sub1,
                     @Param("sub2") String sub2, @Param("img") String img, @Param("price") int price, @Param("discount") int discount,
                     @Param("hot") boolean hot, @Param("isNew") boolean isNew, @Param("racking") boolean racking, @Param("rack_start") int rack_start,
                     @Param("rack_end") int rack_end,@Param("creat_time") int creat_time);

    @Update("UPDATE book set tags=#{tags},sub1=#{sub1},sub2=#{sub2}," +
            "img=#{img},price=#{price},discount=#{discount},hot=#{hot},new=#{isNew},racking=#{racking},rack_start=#{rack_start},rack_end=#{rack_end} where id = #{id}")
    int updateBook(@Param("tags") String tags, @Param("sub1") String sub1,
                   @Param("sub2") String sub2, @Param("img") String img, @Param("price") int price, @Param("discount") int discount,
                   @Param("hot") boolean hot, @Param("isNew") boolean isNew, @Param("racking") boolean racking, @Param("rack_start") int rack_start,
                   @Param("rack_end") int rack_end,@Param("id") int id);

    @Delete("DELETE FROM book WHERE ID = #{id}")
    int deleteBook(@Param("id") int id);



}
