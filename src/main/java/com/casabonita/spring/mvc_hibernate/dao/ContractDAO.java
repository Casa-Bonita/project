package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Contract;

import java.util.List;

public interface ContractDAO {

    public List<Contract> getAllContracts();

    public void saveContract(Contract contract);

    public Contract getContract(int id);

    public void deleteContract(int id);
}
