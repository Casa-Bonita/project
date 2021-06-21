package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.*;
import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService{

    private final AccountDAO accountDAO;
    private final ContractDAO contractDAO;
    private final PaymentDAO paymentDAO;
    private final PlaceDAO placeDAO;
    private final RenterDAO renterDAO;

    public ContractServiceImpl(AccountDAO accountDAO, ContractDAO contractDAO, PaymentDAO paymentDAO, PlaceDAO placeDAO, RenterDAO renterDAO) {
        this.accountDAO = accountDAO;
        this.contractDAO = contractDAO;
        this.paymentDAO = paymentDAO;
        this.placeDAO = placeDAO;
        this.renterDAO = renterDAO;
    }

    @Override
    @Transactional
    public List<Contract> getAllContracts() {

        return contractDAO.getAllContracts();
    }

    @Override
    @Transactional
    public void saveContract(Contract contract, int contractPlaceNumber, String renterName) {

        Contract contractToSave;

        if(contract.getId() == null){
            contractToSave = new Contract();
        } else{
            contractToSave = contractDAO.getContract(contract.getId());
        }

        contractToSave.setNumber(contract.getNumber());
        contractToSave.setDate(contract.getDate());
        contractToSave.setFare(contract.getFare());
        contractToSave.setStartDate(contract.getStartDate());
        contractToSave.setFinishDate(contract.getFinishDate());
        contractToSave.setPaymentDay(contract.getPaymentDay());

        Place place = placeDAO.getPlaceByNumber(contractPlaceNumber);
        contractToSave.setContractPlace(place);

        Renter renter = renterDAO.getRenterByName(renterName);
        contractToSave.setRenter(renter);

        contractDAO.saveContract(contractToSave);

    }

    @Override
    @Transactional
    public Contract getContract(Integer id) {

        return contractDAO.getContract(id);
    }

    @Override
    @Transactional
    public Contract getContractByPlaceId(Integer id) {

        return contractDAO.getContractByPlaceId(id);
    }

    @Override
    @Transactional
    public List<Contract> getContractByRenterId(Integer id) {

        return contractDAO.getContractByRenterId(id);
    }

    @Override
    @Transactional
    public Contract getContractByNumber(String number) {

        return contractDAO.getContractByNumber(number);
    }

    @Override
    @Transactional
    public void deleteContractById(Integer id) {

        Account account = accountDAO.getAccountByContractId(id);
        Integer accountId;

        if(account != null){
            accountId = account.getId();
            paymentDAO.deletePaymentByAccountId(accountId);
            accountDAO.deleteAccountById(accountId);
        }

        contractDAO.deleteContractById(id);
    }
}
