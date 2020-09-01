package com.banking.service;

import com.banking.dao.UserDao;
import com.banking.dao.imp.UserDaoImp;
import com.banking.model.User;
import com.banking.security.BankSecurity;

public class BankingService{
    private static UserDao userDao = new UserDaoImp();

    public static User login(String username,String password) {
        User user = userDao.findUserByUsername(username);
        BankSecurity bankSecurity = new BankSecurity();
        if(user != null){
            if(!user.getPassword().equals(bankSecurity.passwordHash(password))){
                user = null;
            }
        }
        return user;
    }

    public static User register(User user) {
       if(userDao.findUserByUsername(user.getUsername()) != null){
           user = null;
       }else {
           BankSecurity bankSecurity = new BankSecurity();
           user.setPassword(bankSecurity.passwordHash(user.getPassword()));
            userDao.createUser(user);
       }
       return user;
    }

    public static void withdraw(int accountId, double amount) {

    }

    public static void deposit(int accountId, double amount) {

    }

    public static void transfer(int sourceId, int targetId, double amount) {

    }
}
