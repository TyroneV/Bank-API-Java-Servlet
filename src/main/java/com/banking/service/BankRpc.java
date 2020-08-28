package com.banking.service;

import com.banking.model.User;

public interface BankRpc {
    //Remote Procedure Call
    User login(String username,String password);

    void logout();

    User register(User user);

    void withdraw(int accountId, double amount);

    void deposit(int accountId, double amount);

    void transfer(int sourceId, int targetId, double amount);

}
