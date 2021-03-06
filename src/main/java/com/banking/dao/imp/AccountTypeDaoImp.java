package com.banking.dao.imp;

import com.banking.dao.AccountTypeDao;
import com.banking.model.AccountStatus;
import com.banking.model.AccountType;
import com.banking.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountTypeDaoImp implements AccountTypeDao {
    @Override
    public AccountType findAccountType(int accountTypeId) {
        AccountType accountType = new AccountType();
        String sql = "select type_id,type from "
                + ConnectionManager.SCHEMA+
                ".account_type where type_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,accountTypeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                accountType.setTypeId(rs.getInt("type_id"));
                accountType.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountType;
    }

    @Override
    public List<AccountType> findAccountType() {
        List<AccountType> accountTypeList = new ArrayList<>();
        String sql = "select type_id,type from "
                + ConnectionManager.SCHEMA+
                ".account_type";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                accountTypeList.add(
                        new AccountType(rs.getInt("type_id"),
                                rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountTypeList;
    }
}
