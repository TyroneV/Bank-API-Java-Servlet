package com.banking.dao.imp;

import com.banking.dao.AccountDao;
import com.banking.dao.UserAccountDao;
import com.banking.dao.UserDao;
import com.banking.model.Account;
import com.banking.model.User;
import com.banking.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDaoImp implements UserAccountDao {

    @Override
    public List<User> findUsersByAccount(int accountId) {
        List<User> userList = new ArrayList<>();
        String sql = "select user_id from "+
                ConnectionManager.SCHEMA +
                ".user_account where account_id = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1,accountId);
            ResultSet rs = ps.executeQuery();
            UserDao userDao = new UserDaoImp();

            while (rs.next()){
                userList.add(userDao.findUserById(
                        rs.getInt("user_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public List<Account> findAccountsByUser(int userId) {
        List<Account> accountList = new ArrayList<>();
        String sql = "select account_id from "+
                ConnectionManager.SCHEMA +
                ".user_account where user_id = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            AccountDao accountDao = new AccountDaoImp();

            while(rs.next()){
                accountList.add(accountDao.findAccountById(
                        rs.getInt("account_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public List<Account> findAccountsByUser(int userId, int statusId) {
        List<Account> accountList = new ArrayList<>();

        String sql = "select a.account_id from "
                +ConnectionManager.SCHEMA+ ".user u inner join "
                +ConnectionManager.SCHEMA+".user_account ua on u.user_id = ua.user_id inner join "
                +ConnectionManager.SCHEMA+".account a on ua.account_id = a.account_id inner join "
                +ConnectionManager.SCHEMA+".account_status as2 on as2.status_id = a.account_status_id" +
                " where u.user_id = ? and a.account_status_id = ?;";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,userId);
            ps.setInt(2,statusId);

            ResultSet rs = ps.executeQuery();
            AccountDao accountDao = new AccountDaoImp();
            while(rs.next()){
                accountList.add(accountDao.findAccountById(
                        rs.getInt("account_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public User submitNewUserAccount(int userId, int accountId) {
        User user = null;
        String sql ="insert into "+ConnectionManager.SCHEMA+".user_account(user_id,account_id) values " +
                "(?,?) returning user_id";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,userId);
            ps.setInt(2,accountId);

            ResultSet rs = ps.executeQuery();
            UserDao userDao = new UserDaoImp();
            while(rs.next()){
                user = userDao.findUserById(rs.getInt("user_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
