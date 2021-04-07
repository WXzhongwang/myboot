package com.mybatis.test;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/7
 */
public interface UserMapper2 {

    @Select("select * from user")
    List<User> getAllUsers();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "u"),
            @Result(property = "email", column = "a")
    })
    @Select("select name as u,email as a, id as id from user where id=#{id}")
    User getUserById(Long id);

    @Select("select * from user where name like concat('%',#{name},'%')")
    List<User> getUsersByName(String name);

    @Insert({"insert into user(name,age,email) values(#{name},#{age},#{email})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addUser(User user);

    @Update("update user set name=#{name},age=#{age}, email=#{email} where id=#{id}")
    Integer updateUserById(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteUserById(Integer id);
}