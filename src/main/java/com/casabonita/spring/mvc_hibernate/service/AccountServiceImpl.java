package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountDAO accountDAO;
    private final ContractDAO contractDAO;
    private final PaymentDAO paymentDAO;

    public AccountServiceImpl(AccountDAO accountDAO, ContractDAO contractDAO, PaymentDAO paymentDAO) {
        this.accountDAO = accountDAO;
        this.contractDAO = contractDAO;
        this.paymentDAO = paymentDAO;
    }

    @Override
    @Transactional
    public List<Account> getAllAccounts() {

        return accountDAO.getAllAccounts();
    }

    @Override
    @Transactional
    public void saveAccount(Account account, String accountContractNumber) {

        Account accountToSave;

        if(account.getId() == null){
            accountToSave = new Account();
        } else{
            accountToSave = accountDAO.getAccount(account.getId());
        }

        accountToSave.setNumber(account.getNumber());

        Contract contract = contractDAO.getContractByNumber(accountContractNumber);
        accountToSave.setAccountContract(contract);

        accountDAO.saveAccount(accountToSave);
    }

    @Override
    @Transactional
    public Account getAccount(Integer id) {

        return accountDAO.getAccount(id);
    }

    @Override
    @Transactional
    public Account getAccountByContractId(Integer id) {

        return accountDAO.getAccountByContractId(id);
    }

    @Override
    @Transactional
    public Account getAccountByNumber(String number) {

        return accountDAO.getAccountByNumber(number);
    }

    @Override
    @Transactional
    public void deleteAccountById(Integer id) {

        paymentDAO.deletePaymentByAccountId(id);
        accountDAO.deleteAccountById(id);
    }
}
