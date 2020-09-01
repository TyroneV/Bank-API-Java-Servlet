package com.banking.model;

import java.util.List;

public class Account {
    private int accountId; // primary key
    private double balance;  // not null
    private AccountStatus status;
    private AccountType type;
    private List<User> userList;

    public Account(){

    }

    public Account(AccountStatus status, AccountType type) {
        this.balance = 0;
        this.status = status;
        this.type = type;
    }

    public Account(int accountId, double balance, AccountStatus status, AccountType type) {
        this.accountId = accountId;
        this.balance = balance;
        this.status = status;
        this.type = type;
    }

    public Account(int accountId, double balance, AccountStatus status, AccountType type, List<User> userList) {
        this.accountId = accountId;
        this.balance = balance;
        this.status = status;
        this.type = type;
        this.userList = userList;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
