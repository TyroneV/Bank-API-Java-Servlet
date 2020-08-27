package com.banking.dao.imp;

import com.banking.dao.AccountStatusDao;
import com.banking.model.AccountStatus;
import com.banking.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountStatusDaoImp implements AccountStatusDao {
    @Override
    public AccountStatus findAccountStatus(int accountStatusId) {
        AccountStatus accountStatus = new AccountStatus();
        String sql = "select status_id,status from "
                + ConnectionManager.SCHEMA+
                ".account_status where status_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,accountStatusId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                accountStatus.setStatusId(rs.getInt("status_id"));
                accountStatus.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountStatus;
    }
}
