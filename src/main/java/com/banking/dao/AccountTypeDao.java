package com.banking.dao;

import com.banking.model.AccountType;

import java.util.List;

public interface AccountTypeDao {
    AccountType findAccountType(int accountTypeId);
    List<AccountType> findAccountType();
}
