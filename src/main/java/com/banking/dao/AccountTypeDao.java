package com.banking.dao;

import com.banking.model.AccountType;

public interface AccountTypeDao {
    AccountType findAccountType(int accountTypeId);
}
