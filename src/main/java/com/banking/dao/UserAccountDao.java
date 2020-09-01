package com.banking.dao;

import com.banking.model.Account;
import com.banking.model.User;
import java.util.List;

public interface UserAccountDao {
    //READ
    List<User> findUsersByAccount(int accountId);//Admin or Employee, User if The user owns the account

    List<Account> findAccountsByUser(int userId);//Admin or Employee, User if The user owns the account

    List<Account> findAccountsByUser(int userId,int statusId);//Admin or Employee, User if The user owns the account

    //Create

    User submitNewUserAccount(int user, int accountId);
}
