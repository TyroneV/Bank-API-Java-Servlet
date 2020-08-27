package com.banking.service;

import com.banking.model.User;

public class BankingService implements BankRpc{
    @Override
    public User login() {
        return null;
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
