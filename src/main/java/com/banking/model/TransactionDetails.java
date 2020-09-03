package com.banking.model;

import java.sql.Date;

public class TransactionDetails {

    int transactionId;
    TransactionType transactionType;
    double transactionAmount;
    double transactionBalance;
    Date transactionDate;
    int sourceAccountId;
    int targetAccountId;

    public TransactionDetails() {
    }

    public TransactionDetails(TransactionType transactionType, double transactionAmount, double transactionBalance, Date transactionDate, int sourceAccountId, int targetAccountId) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionBalance = transactionBalance;
        this.transactionDate = transactionDate;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
    }

    public TransactionDetails(int transactionId, TransactionType transactionType, double transactionAmount, double transactionBalance, Date transactionDate, int sourceAccountId, int targetAccountId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionBalance = transactionBalance;
        this.transactionDate = transactionDate;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
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
        return sourceAccountId;
    }

    public void setAccountId(int accountId) {
        this.sourceAccountId = accountId;
    }

    public int getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(int sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public int getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(int targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public double getTransactionBalance() {
        return transactionBalance;
    }

    public void setTransactionBalance(double transactionBalance) {
        this.transactionBalance = transactionBalance;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", transactionAmount=" + transactionAmount +
                ", transactionBalance=" + transactionBalance +
                ", transactionDate=" + transactionDate +
                ", sourceAccountId=" + sourceAccountId +
                ", targetAccountId=" + targetAccountId +
                '}';
    }
}
