package com.banking.dao;

import com.banking.model.Account;

import java.util.List;

public interface AccountDao {

    //CREATE
    Account submitAccount(Account account);//Admin or Employee
    //READ
    List<Account> findAccounts();//Admin or Employee

    Account findAccountById(int accountId);//Admin or Employee, User if The user owns the account

    List<Account> findAccountsByStatus(int statusId);//Admin or Employee

    /* moved to UserAccountDao
    List<Account> findAccountsByUser(int userId);//Admin or Employee, User if The user owns the account

    List<Account> findAccountsByUser(int userId,int statusId);//Admin or Employee, User if The user owns the account
     */

    //UPDATE
    Account updateAccount(Account account);//Admin only
    //DELETE
    Account deleteAccount(Account account);//Admin only

}
