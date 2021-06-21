package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Account;

import java.util.List;

public interface AccountDAO {

    List<Account> getAllAccounts();

    void saveAccount(Account account);

    Account getAccount(Integer id);

    Account getAccountByContractId(Integer id);

    Account getAccountByNumber(String number);

    void deleteAccountById(Integer id);

}
