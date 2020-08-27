package com.banking.dao;

import com.banking.model.TransactionDetails;

import java.util.List;

public interface TransactionDetailsDao {

    //READs
    List<TransactionDetails> findTransactionDetails();

    List<TransactionDetails> findTransactionsByAccount(int accountId);
    //Create
    TransactionDetails createTransaction(TransactionDetails transaction);
}
