package com.banking.dao;

import com.banking.model.AccountStatus;

public interface AccountStatusDao {
    AccountStatus findAccountStatus(int accountStatusId);
}
