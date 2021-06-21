package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    void saveAccount(Account account, String accountContractNumber);

    Account getAccount(Integer id);

    Account getAccountByContractId(Integer id);

    Account getAccountByNumber(String number);

    void deleteAccountById(Integer id);

}
