package com;

import com.banking.dao.*;
import com.banking.dao.imp.UserAccountDaoImp;
import com.banking.dao.imp.UserDaoImp;
import com.banking.model.User;

public class Driver {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImp();
        for (User s:userDao.findUsers()) {
            System.out.println(s);
        }

        UserAccountDao userAccountDao = new UserAccountDaoImp();
        System.out.println(userAccountDao.findAccountsByUser(1));

//        AccountDao accountDao = new AccountDaoImp();
//        System.out.println(accountDao.findAccountById(1));


    }
}
