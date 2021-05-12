package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountDAO accountDAO;
    private final ContractDAO contractDAO;

    public AccountServiceImpl(AccountDAO accountDAO, ContractDAO contractDAO) {
        this.accountDAO = accountDAO;
        this.contractDAO = contractDAO;
    }

    @Override
    @Transactional
    public List<Account> getAllAccounts() {

        return accountDAO.getAllAccounts();
    }

    @Override
    @Transactional
    public void saveAccount(Account account, String accountContractNumber) {

        Contract contract = contractDAO.getContractByNumber(accountContractNumber);

        account.setAccountContract(contract);

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

    @Override
    @Transactional
    public Account getAccountByNumber(String number) {

        return accountDAO.getAccountByNumber(number);
    }
}
