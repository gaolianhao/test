package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {
    @Select("SELECT id,name,email,password FROM user where name = #{name}")
    User getUser(String name);

    @Select("SELECT id,name,email,password FROM user where email = #{email}")
    User getUserByEmail(String email);

    @Insert("INSERT INTO user (id, name, email, password) VALUES(#{id}, #{name}, #{email}, #{password})")
    void insertUser(User user);
}
