package com.casabonita.spring.mvc_hibernate.dao;

import com.casabonita.spring.mvc_hibernate.entity.Contract;

import java.util.List;

public interface ContractDAO {

    List<Contract> getAllContracts();

    void saveContract(Contract contract);

    Contract getContract(Integer id);

    Contract getContractByPlaceId(Integer id);

    List <Contract> getContractByRenterId(Integer id);

    Contract getContractByNumber (String number);

    void deleteContractById(Integer id);
}
