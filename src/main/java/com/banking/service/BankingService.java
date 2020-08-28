package com.banking.service;

import com.banking.dao.UserDao;
import com.banking.dao.imp.UserDaoImp;
import com.banking.model.User;

public class BankingService implements BankRpc{
    @Override
    public User login(String username,String password) {
        UserDao userDao = new UserDaoImp();
        User user = userDao.findUserByUsername(username);
        if(user != null){
            if(!user.getPassword().equals(password)){
                user = null;
            }
        }
        return user;
    }
    @Override
    public void logout() {

    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public void withdraw(int accountId, double amount) {

    }

    @Override
    public void deposit(int accountId, double amount) {

    }

    @Override
    public void transfer(int sourceId, int targetId, double amount) {

    }
}
