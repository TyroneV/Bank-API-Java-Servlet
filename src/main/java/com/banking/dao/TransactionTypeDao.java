package com.banking.dao;
import com.banking.model.TransactionType;

public interface TransactionTypeDao {
    TransactionType findTransactionType(int transaction_id);
}
