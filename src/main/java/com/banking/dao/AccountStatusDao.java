package com.banking.dao;

import com.banking.model.AccountStatus;

import java.util.List;

public interface AccountStatusDao {
    AccountStatus findAccountStatus(int accountStatusId);
    List<AccountStatus> findAccountStatus();
}
