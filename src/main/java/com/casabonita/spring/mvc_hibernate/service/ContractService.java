package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.entity.Contract;

import java.util.List;

public interface ContractService {

    List<Contract> getAllContracts();

    void saveContract(Contract contract);

    Contract getContract (int id);

    void deleteContract(int id);
}
