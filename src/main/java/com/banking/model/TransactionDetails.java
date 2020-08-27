package com.banking.model;

import java.sql.Date;

public class TransactionDetails {

    int transactionId;
    TransactionType transactionType;
    double transactionAmount;
    Date transactionDate;
    int accountId;

    public TransactionDetails() {
    }

    public TransactionDetails(TransactionType transactionType, double transactionAmount, int accountId) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.accountId = accountId;
    }

    public TransactionDetails(TransactionType transactionType, double transactionAmount, Date transactionDate, int accountId) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.accountId = accountId;
    }

    public TransactionDetails(int transactionId, TransactionType transactionType, double transactionAmount, Date transactionDate, int accountId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.accountId = accountId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
