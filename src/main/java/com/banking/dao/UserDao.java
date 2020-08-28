package com.banking.dao;

import com.banking.model.User;

import java.util.List;

public interface UserDao {

    //CREATE
    User createUser(User user);

    //UPDATE
    User updateUser(User user);//Admin

    //READ
    List<User> findUsers();//Admin or Employee

    User findUserByUsername(String username);

    User findUserById(int userId);//Admin or Employee

    //DELETE
    User deleteUser(User user);//Admin
}
