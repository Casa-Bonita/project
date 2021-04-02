package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Account;

import java.util.List;

public interface AccountDAO {

    public List<Account> getAllAccounts();

    public void saveAccount(Account account);

    public Account getAccount(int id);

    public void deleteAccount(int id);
}
