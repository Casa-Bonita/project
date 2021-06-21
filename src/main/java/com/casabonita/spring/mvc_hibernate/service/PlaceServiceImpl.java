package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.dao.*;
import com.casabonita.spring.mvc_hibernate.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{

    private final AccountDAO accountDAO;
    private final ContractDAO contractDAO;
    private final MeterDAO meterDAO;
    private final PaymentDAO paymentDAO;
    private final PlaceDAO placeDAO;
    private final ReadingDAO readingDAO;

    public PlaceServiceImpl(AccountDAO accountDAO, ContractDAO contractDAO, MeterDAO meterDAO, PaymentDAO paymentDAO, PlaceDAO placeDAO, ReadingDAO readingDAO) {
        this.accountDAO = accountDAO;
        this.contractDAO = contractDAO;
        this.meterDAO = meterDAO;
        this.paymentDAO = paymentDAO;
        this.placeDAO = placeDAO;
        this.readingDAO = readingDAO;
    }

    @Override
    @Transactional
    public List<Place> getAllPlaces() {

        return placeDAO.getAllPlaces();
    }

    @Override
    @Transactional
    public void savePlace(Place place) {

        placeDAO.savePlace(place);
    }

    @Override
    @Transactional
    public Place getPlaceById(Integer id) {

        return placeDAO.getPlaceById(id);
    }

    @Override
    @Transactional
    public Place getPlaceByNumber(int number) {

        return placeDAO.getPlaceByNumber(number);
    }

    @Override
    @Transactional
    public void deletePlaceById(Integer id) {

        Meter meter = meterDAO.getMeterByPlaceId(id);
        Integer meterId;

        Contract contract = contractDAO.getContractByPlaceId(id);
        Integer contractId;

        if(contract != null){
            Account account = accountDAO.getAccountByContractId(contract.getId());
            Integer accountId;

            if(account != null){
                accountId = account.getId();
                paymentDAO.deletePaymentByAccountId(accountId);
                accountDAO.deleteAccountById(accountId);
            }

            contractId = contract.getId();
            contractDAO.deleteContractById(contractId);
        }

        if(meter != null){
            meterId = meter.getId();
            readingDAO.deleteReadingByMeterId(meterId);
            meterDAO.deleteMeterById(meterId);
        }

        placeDAO.deletePlaceById(id);

    }
}
