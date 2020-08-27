package com.banking.model;

import java.sql.Date;

public class Transfer {
    int transferId;
    double transferAmount;
    Date transferDate;
    int accountSourceId;
    int accountTargetId;

    public Transfer() {
    }

    public Transfer(double transferAmount, Date transferDate, int accountSourceId, int accountTargetId) {
        this.transferAmount = transferAmount;
        this.transferDate = transferDate;
        this.accountSourceId = accountSourceId;
        this.accountTargetId = accountTargetId;
    }

    public Transfer(int transferId, double transferAmount, Date transferDate, int accountSourceId, int accountTargetId) {
        this.transferId = transferId;
        this.transferAmount = transferAmount;
        this.transferDate = transferDate;
        this.accountSourceId = accountSourceId;
        this.accountTargetId = accountTargetId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public int getAccountSourceId() {
        return accountSourceId;
    }

    public void setAccountSourceId(int accountSourceId) {
        this.accountSourceId = accountSourceId;
    }

    public int getAccountTargetId() {
        return accountTargetId;
    }

    public void setAccountTargetId(int accountTargetId) {
        this.accountTargetId = accountTargetId;
    }
}
