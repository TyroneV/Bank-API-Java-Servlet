package com.banking.dao.imp;

import com.banking.dao.AccountDao;
import com.banking.dao.AccountStatusDao;
import com.banking.dao.AccountTypeDao;
import com.banking.dao.UserAccountDao;
import com.banking.model.Account;
import com.banking.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImp implements AccountDao {

    @Override
    public Account submitAccount(Account account) {

        String sql = "insert into "+ ConnectionManager.SCHEMA+".account " +
                "(account_balance,account_status_id,account_type_id)" +
                "values(?,?,?) " +
                "returning account_id,account_balance,account_status_id,account_type_id";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1,account.getBalance());
            ps.setInt(2,account.getStatus().getStatusId());
            ps.setInt(3,account.getType().getTypeId());
            ResultSet rs = ps.executeQuery();
            AccountStatusDao accountStatusDao = new AccountStatusDaoImp();
            AccountTypeDao accountTypeDao = new AccountTypeDaoImp();
            while (rs.next()){
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("account_balance"));
                account.setStatus(accountStatusDao.findAccountStatus(rs.getInt("account_status_id")));
                account.setType(accountTypeDao.findAccountType(rs.getInt("account_type_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> findAccounts() {
        String sql = "select * from "+ ConnectionManager.SCHEMA+".account ";
        List<Account> accountList = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            AccountStatusDao accountStatusDao = new AccountStatusDaoImp();
            AccountTypeDao accountTypeDao = new AccountTypeDaoImp();
            UserAccountDao userAccountDao = new UserAccountDaoImp();
            while (rs.next()){
                accountList.add(new Account(rs.getInt("account_id"),
                        rs.getDouble("account_balance"),
                        accountStatusDao.findAccountStatus(rs.getInt("account_status_id")),
                        accountTypeDao.findAccountType(rs.getInt("account_type_id")),
                        userAccountDao.findUsersByAccount(rs.getInt("account_id"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public Account findAccountById(int accountId) {
        String sql = "select * from "+ ConnectionManager.SCHEMA+".account where account_id = ?";
        Account account = new Account();
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,accountId);
            ResultSet rs = ps.executeQuery();
            AccountStatusDao accountStatusDao = new AccountStatusDaoImp();
            AccountTypeDao accountTypeDao = new AccountTypeDaoImp();
            while (rs.next()){
                account.setAccountId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("account_balance"));
                account.setStatus(accountStatusDao.findAccountStatus(rs.getInt("account_status_id")));
                account.setType(accountTypeDao.findAccountType(rs.getInt("account_type_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> findAccountsByStatus(int statusId) {
        String sql = "select * from "+ ConnectionManager.SCHEMA+".account where account_status_id = ?";
        List<Account> accountList = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,statusId);

            ResultSet rs = ps.executeQuery();
            AccountStatusDao accountStatusDao = new AccountStatusDaoImp();
            AccountTypeDao accountTypeDao = new AccountTypeDaoImp();

            while (rs.next()){
                accountList.add(new Account(rs.getInt("account_id"),
                        rs.getDouble("account_balance"),
                        accountStatusDao.findAccountStatus(rs.getInt("account_status_id")),
                        accountTypeDao.findAccountType(rs.getInt("account_type_id"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public Account updateAccount(Account account) {
        String sql = "update "+ ConnectionManager.SCHEMA+".account " +
                "set account_balance = ?, " +
                "account_status_id = ?, account_type_id = ? " +
                "where account_id = ?";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1,account.getBalance());
            ps.setInt(2,account.getStatus().getStatusId());
            ps.setInt(3,account.getType().getTypeId());
            ps.setInt(4,account.getAccountId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account deleteAccount(Account account) {
        String sql = "delete from "+ConnectionManager.SCHEMA+".user where user_id = ?";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,account.getAccountId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }
}
