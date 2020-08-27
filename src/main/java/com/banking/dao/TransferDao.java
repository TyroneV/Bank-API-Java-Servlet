package com.banking.dao;

import com.banking.model.Transfer;

import java.util.List;

public interface TransferDao {

    //READ
    List<Transfer> findTransferBySourceId(int accountSourceId);

    List<Transfer> findTransferByTargetId(int accountTargetId);

    List<Transfer> findTransfers();

    //CREATE
    Transfer createTransfer(Transfer transfer);
}
