package com.banking.model;

public class TransactionType {
    int transactionId;
    String transactionName;

    public TransactionType() {
    }

    public TransactionType(String transactionName) {
        this.transactionName = transactionName;
    }

    public TransactionType(int transactionId, String transactionName) {
        this.transactionId = transactionId;
        this.transactionName = transactionName;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
}
