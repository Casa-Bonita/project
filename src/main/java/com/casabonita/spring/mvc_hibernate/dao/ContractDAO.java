package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Contract;

import java.util.List;

public interface ContractDAO {

    List<Contract> getAllContracts();

    void saveContract(Contract contract);

    Contract getContract(int id);

    void deleteContract(int id);

    Contract getContractByNumber (String number);
}
