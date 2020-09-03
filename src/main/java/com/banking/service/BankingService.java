package com.banking.service;

import com.banking.dao.*;
import com.banking.dao.imp.*;
import com.banking.model.*;
import com.banking.security.BankSecurity;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class BankingService{

    //Dao Objects
    private static UserDao userDao = new UserDaoImp();
    private static UserAccountDao userAccountDao = new UserAccountDaoImp();
    private static AccountDao accountDao = new AccountDaoImp();
    private static AccountTypeDao accountTypeDao = new AccountTypeDaoImp();
    private static AccountStatusDao accountStatusDao = new AccountStatusDaoImp();
    private static TransactionDetailsDao transactionDetailsDao = new TransactionDetailsDaoImp();
    private static TransactionTypeDao transactionTypeDao = new TransactionTypeDaoImp();
    private static RoleDao roleDao = new RoleDaoImp();

    //Main Methods
    public static User login(String username,String password) {
        User user = userDao.findUserByUsername(username);
        BankSecurity bankSecurity = new BankSecurity();
        if(user != null){
            if(!user.getPassword().equals(bankSecurity.passwordHash(password))){
                user = null;
            }else {
                user.setAccounts(userAccountDao.findAccountsByUser(user.getUserId()));
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

    public static Account submitAccount(Account account,User user){
        try {
            accountDao.submitAccount(account);
            userAccountDao.submitNewUserAccount(user.getUserId(), account.getAccountId());
        } catch (Exception e){
            account = null;
        }
        return account;
    }

    public static Account getAccountWithUsers(int accountId){
        Account account = accountDao.findAccountById(accountId);
        account.setUserList(userAccountDao.findUsersByAccount(accountId));
        return account;
    }

    public static List<TransactionDetails> getTransactionDetailsByAccountId(int accountId){
        return transactionDetailsDao.findTransactionsByAccount(accountId);
    }

    public static List<Account> listOfPendingAccounts(int userId){
        List<Account> accountList = userAccountDao.findAccountsByUser(userId,1);
        return accountList;
    }

    public static Account withdraw(int accountId, double amount) {
        Account account = accountDao.findAccountById(accountId);
        if(account.getBalance() >= amount && amount > 0){
            TransactionType transactionType = transactionTypeDao.findTransactionType(1);

            TransactionDetails transaction =
                    new TransactionDetails(transactionType,amount,
                            account.getBalance(),getTime(),accountId,accountId);
            account.setBalance(account.getBalance() - amount);
            //Begin Updating
            if(transactionDetailsDao.createTransaction(transaction)!= null) {
                accountDao.updateAccount(account);
            }
        } else {
            account = null;
        }
        return account;
    }

    public static Account deposit(int accountId, double amount) {
        Account account = accountDao.findAccountById(accountId);
        if(0 < amount){
            TransactionType transactionType = transactionTypeDao.findTransactionType(2);

            TransactionDetails transaction =
                    new TransactionDetails(transactionType,amount,
                            account.getBalance(),getTime(),accountId,accountId);
            account.setBalance(account.getBalance() + amount);
            //Begin Updating
            if(transactionDetailsDao.createTransaction(transaction) != null) {
                accountDao.updateAccount(account);
            }
        } else {
            account = null;
        }
        return account;
    }

    public static Account transfer(int sourceId, int targetId, double amount) {
        Account sourceAccount = accountDao.findAccountById(sourceId);
        Account targetAccount = accountDao.findAccountById(targetId);
        if(0 < amount && findAccount(targetId) != null && targetAccount.getStatus().getStatusId() == 2 ){
            TransactionType transactionType = transactionTypeDao.findTransactionType(3);

            TransactionDetails transaction =
                    new TransactionDetails(transactionType,amount,
                            sourceAccount.getBalance(),getTime(),sourceId,targetId);
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            targetAccount.setBalance(targetAccount.getBalance() + amount);
            //Begin Updating
            if(transactionDetailsDao.createTransaction(transaction) != null) {
                accountDao.updateAccount(sourceAccount);
                accountDao.updateAccount(targetAccount);
            }
        } else {
            sourceAccount = null;
        }
        return sourceAccount;
    }
    //Update Methods

    public static User updateUser(User user){
       return userDao.updateUser(user);
    }

    public static Account updateAccount(Account account){
        return accountDao.updateAccount(account);
    }

    //Find Methods
    public static Role findRole(int roleId){
        return roleDao.findRole(roleId);
    }
    public static User findUser(int userId){
        User user = userDao.findUserById(userId);
        user.setAccounts(userAccountDao.findAccountsByUser(userId));
       return user;
    }
    public static User findUserByUsername(String username){
        return userDao.findUserByUsername(username);
    }
    public static List<User> findUsers(){
        List<User> userList = userDao.findUsers();
        for (User u: userList) {
            u.setAccounts(userAccountDao.findAccountsByUser(u.getUserId()));
        }
        return userList;
    }
    public static Account findAccount(int accountId){
        Account account = accountDao.findAccountById(accountId);
        account.setUserList(userAccountDao.findUsersByAccount(accountId));
        return account;
    }
    public static List<Account> findAccounts(){
        List<Account> accountList =  accountDao.findAccounts();
        for (Account a: accountList) {
            a.setUserList(userAccountDao.findUsersByAccount(a.getAccountId()));
        }
        return accountList;
    }

    public static List<Account> findAccountsByStatusId(int statusId){
        List<Account> accountList = accountDao.findAccountsByStatus(statusId);
        for (Account a: accountList) {
            a.setUserList(userAccountDao.findUsersByAccount(a.getAccountId()));
        }
        return accountList;
    }

    public static AccountStatus findStatus(int statusId){
        return accountStatusDao.findAccountStatus(statusId);
    }
    public static AccountType findType(int typeId){
        return accountTypeDao.findAccountType(typeId);
    }

    public static List<Account> findAccountsByUserId(int userId){
        List<Account> accountList = userAccountDao.findAccountsByUser(userId);
        for (Account a: accountList) {
            a.setUserList(userAccountDao.findUsersByAccount(a.getAccountId()));
        }
        return accountList;
    }

    public static List<Role> findRoles(){
        return roleDao.findRoles();
    }

    public static List<AccountType> findAccountTypes(){
        return accountTypeDao.findAccountType();
    }
    public static List<AccountStatus> findAccountStatus(){
        return accountStatusDao.findAccountStatus();
    }

    //Utility Methods
    static Date getTime(){
        return new Date(Calendar.getInstance().getTimeInMillis());
    }
}
