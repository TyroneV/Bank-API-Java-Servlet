package com.banking.dao.imp;

import com.banking.dao.TransactionTypeDao;
import com.banking.model.TransactionType;
import com.banking.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTypeDaoImp implements TransactionTypeDao {
    @Override
    public TransactionType findTransactionType(int transaction_id) {
        TransactionType transactionType = null;
        String sql ="select * from "
                +ConnectionManager.SCHEMA+".transaction_type where transaction_id = ?";
        try ( Connection conn = ConnectionManager.getConnection();
              PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,transaction_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                transactionType =
                        new TransactionType(
                                rs.getInt("transaction_id"),
                                rs.getString("transaction_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionType;
    }
}
