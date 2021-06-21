package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.AccountDAO;
import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PaymentDAO;
import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RenterServiceImpl implements RenterService{

    private final AccountDAO accountDAO;
    private final ContractDAO contractDAO;
    private final PaymentDAO paymentDAO;
    private final RenterDAO renterDAO;

    public RenterServiceImpl(AccountDAO accountDAO, ContractDAO contractDAO, PaymentDAO paymentDAO, RenterDAO renterDAO) {
        this.accountDAO = accountDAO;
        this.contractDAO = contractDAO;
        this.paymentDAO = paymentDAO;
        this.renterDAO = renterDAO;
    }

    @Override
    @Transactional
    public List<Renter> getAllRenters() {

        return renterDAO.getAllRenters();
    }

    @Override
    @Transactional
    public void saveRenter(Renter renter) {

        renterDAO.saveRenter(renter);
    }

    @Override
    @Transactional
    public Renter getRenter(Integer id) {

        return renterDAO.getRenter(id);
    }

    @Override
    @Transactional
    public Renter getRenterByName(String name) {

        return renterDAO.getRenterByName(name);
    }

    @Override
    @Transactional
    public void deleteRenterById(Integer id) {

        List<Contract> contractList = contractDAO.getContractByRenterId(id);
        Contract contract;
        Integer contractId;
        Account account;
        Integer accountId;

        if (contractList.isEmpty()) {
            renterDAO.deleteRenterById(id);
        }else{
            for (int i = 0; i < contractList.size(); i++) {
                contract = contractList.get(i);
                contractId = contract.getId();

                account = accountDAO.getAccountByContractId(contractId);
                accountId = account.getId();

                if(account == null){
                    contractDAO.deleteContractById(contractId);
                    renterDAO.deleteRenterById(id);
                }else{
                    paymentDAO.deletePaymentByAccountId(accountId);
                    accountDAO.deleteAccountById(accountId);
                    contractDAO.deleteContractById(contractId);
                    renterDAO.deleteRenterById(id);
                }
            }
        }
    }
}
