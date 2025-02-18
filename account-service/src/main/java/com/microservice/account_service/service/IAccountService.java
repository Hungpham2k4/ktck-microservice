package com.microservice.account_service.service;

import com.microservice.account_service.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getListAccounts();
    Account findAccountById(int id);
    Account createAccount(Account account);
}
