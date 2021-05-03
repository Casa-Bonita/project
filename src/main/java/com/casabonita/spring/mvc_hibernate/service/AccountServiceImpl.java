package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

//    @Autowired
    private final AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    @Transactional
    public List<Account> getAllAccounts() {

        return accountDAO.getAllAccounts();
    }

    @Override
    @Transactional
    public void saveAccount(Account account) {

        accountDAO.saveAccount(account);
    }

    @Override
    @Transactional
    public Account getAccount(int id) {

        return accountDAO.getAccount(id);
    }

    @Override
    @Transactional
    public void deleteAccount(int id) {

        accountDAO.deleteAccount(id);
    }
}
