package com.banking.dao.imp;

import com.banking.dao.TransactionTypeDao;
import com.banking.dao.TransferDao;
import com.banking.model.Transfer;
import com.banking.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransferDaoImp implements TransferDao {

    @Override
    public List<Transfer> findTransferBySourceId(int accountSourceId) {
        List<Transfer> transferList = new ArrayList<>();
        String sql ="select * from "
                + ConnectionManager.SCHEMA+".transfer where = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,accountSourceId);
            ResultSet rs = ps.executeQuery();
            TransactionTypeDao transactionTypeDao = new TransactionTypeDaoImp();
            while (rs.next()){
                transferList.add(
                    new Transfer(rs.getInt("transfer_id"),
                            rs.getDouble("transfer_amount"),
                            rs.getDate("transfer_date"),
                            rs.getInt("account_source_id"),
                            rs.getInt("account_target_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transferList;
    }

    @Override
    public List<Transfer> findTransferByTargetId(int accountTargetId) {
        List<Transfer> transferList = new ArrayList<>();
        String sql ="select * from "
                + ConnectionManager.SCHEMA+".transfer where = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,accountTargetId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                transferList.add(
                        new Transfer(rs.getInt("transfer_id"),
                                rs.getDouble("transfer_amount"),
                                rs.getDate("transfer_date"),
                                rs.getInt("account_source_id"),
                                rs.getInt("account_target_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transferList;
    }

    @Override
    public List<Transfer> findTransfers() {
        List<Transfer> transferList = new ArrayList<>();
        String sql ="select * from "
                + ConnectionManager.SCHEMA+".transfer";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                transferList.add(
                        new Transfer(                   rs.getInt("transfer_id"),
                                rs.getDouble("transfer_amount"),
                                rs.getDate("transfer_date"),
                                rs.getInt("account_source_id"),
                                rs.getInt("account_target_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transferList;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        Transfer newTransfer = null;
        String sql ="select * from "
                + ConnectionManager.SCHEMA+".transfer";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            TransactionTypeDao transactionTypeDao = new TransactionTypeDaoImp();
            while (rs.next()){
                newTransfer = new Transfer(
                        rs.getInt("transfer_id"),
                        rs.getDouble("transfer_amount"),
                        rs.getDate("transfer_date"),
                        rs.getInt("account_source_id"),
                        rs.getInt("account_target_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newTransfer;
    }
}
