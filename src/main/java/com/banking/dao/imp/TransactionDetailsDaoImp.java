package com.banking.dao.imp;

import com.banking.dao.TransactionDetailsDao;
import com.banking.dao.TransactionTypeDao;
import com.banking.model.TransactionDetails;
import com.banking.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailsDaoImp implements TransactionDetailsDao {
    @Override
    public List<TransactionDetails> findTransactionDetails() {
        List<TransactionDetails> transactions = new ArrayList<>();
        String sql ="select * from "
                + ConnectionManager.SCHEMA+".transaction_details";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            TransactionTypeDao transactionTypeDao = new TransactionTypeDaoImp();
            while (rs.next()){
                transactions.add(
                    new TransactionDetails(
                            rs.getInt("transaction_id"),
                            transactionTypeDao.findTransactionType(rs.getInt("transaction_type_id")),
                            rs.getDouble("transaction_amount"),
                            rs.getDouble("transaction_balance"),
                            rs.getDate("transaction_date"),
                            rs.getInt("source_account_id"),
                            rs.getInt("target_account_id")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<TransactionDetails> findTransactionsByAccount(int accountId) {
        List<TransactionDetails> transactions = new ArrayList<>();
        String sql ="select * from "
                + ConnectionManager.SCHEMA+".transaction_details where source_account_id = ? or target_account_id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,accountId);
            ps.setInt(2,accountId);
            ResultSet rs = ps.executeQuery();
            TransactionTypeDao transactionTypeDao = new TransactionTypeDaoImp();
            while (rs.next()){
                transactions.add(
                        new TransactionDetails(
                                rs.getInt("transaction_id"),
                                transactionTypeDao.findTransactionType(rs.getInt("transaction_type_id")),
                                rs.getDouble("transaction_amount"),
                                rs.getDouble("transaction_balance"),
                                rs.getDate("transaction_date"),
                                rs.getInt("source_account_id"),
                                rs.getInt("target_account_id")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public TransactionDetails createTransaction(TransactionDetails transaction) {
        TransactionDetails transactionDetails = null;
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        String sql ="insert into "
                + ConnectionManager.SCHEMA+".transaction_details (transaction_type_id," +
                "transaction_amount,transaction_balance,transaction_date,source_account_id,target_account_id) values (?,?,?,?,?,?) " +
                "returning transaction_id,transaction_type_id,transaction_amount,transaction_balance,transaction_date,source_account_id,target_account_id";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDouble(1,transaction.getTransactionType().getTransactionId());
            ps.setDouble(2,transaction.getTransactionAmount());
            ps.setDouble(3,transaction.getTransactionBalance());
            ps.setDate(4,date);
            ps.setInt(5,transaction.getSourceAccountId());
            ps.setInt(6,transaction.getTargetAccountId());
            ResultSet rs = ps.executeQuery();
            TransactionTypeDao transactionTypeDao = new TransactionTypeDaoImp();
            while (rs.next()){
                transactionDetails = new TransactionDetails(
                        rs.getInt("transaction_id"),
                        transactionTypeDao.findTransactionType(rs.getInt("transaction_type_id")),
                        rs.getDouble("transaction_amount"),
                        rs.getDouble("transaction_balance"),
                        rs.getDate("transaction_date"),
                        rs.getInt("source_account_id"),
                        rs.getInt("target_account_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionDetails;
    }
}
