package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.ContractDAO;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService{

    @Autowired
    private ContractDAO contractDAO;

    @Override
    @Transactional
    public List<Contract> getAllContracts() {

        return contractDAO.getAllContracts();
    }

    @Override
    @Transactional
    public void saveContract(Contract contract) {

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
}
