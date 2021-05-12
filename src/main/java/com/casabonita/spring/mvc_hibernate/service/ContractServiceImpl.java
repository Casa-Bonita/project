package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.dao.PlaceDAO;
import com.casabonita.spring.mvc_hibernate.dao.RenterDAO;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.entity.Renter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService{

    private final ContractDAO contractDAO;
    private final PlaceDAO placeDAO;
    private final RenterDAO renterDAO;

    public ContractServiceImpl(ContractDAO contractDAO, PlaceDAO placeDAO, RenterDAO renterDAO) {
        this.contractDAO = contractDAO;
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

        Place place = placeDAO.getPlaceByNumber(contractPlaceNumber);

        contract.setContractPlace(place);

        Renter renter = renterDAO.getRenterByName(renterName);

        contract.setRenter(renter);

        contractDAO.saveContract(contract);

    }

    @Override
    @Transactional
    public Contract getContract(int id) {

        return contractDAO.getContract(id);
    }

    @Override
    @Transactional
    public void deleteContract(int id) {

        contractDAO.deleteContract(id);
    }

    @Override
    @Transactional
    public Contract getContractByNumber(String number) {

        return contractDAO.getContractByNumber(number);
    }
}
