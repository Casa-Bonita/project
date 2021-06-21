package com.casabonita.spring.mvc_hibernate.service;

import com.casabonita.spring.mvc_hibernate.Operations;
import com.casabonita.spring.mvc_hibernate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StartService {

    @Autowired
    private PlaceService placeService;

    public List<Summary> getAllSummaries() {

        Operations operations = new Operations();
        Place place;
        List<Place> allPlaces = placeService.getAllPlaces();

        List<Summary> allSummaries = new ArrayList<>();

        for (int i = 0; i < allPlaces.size(); i++) {

            Summary summary = new Summary();

            place = allPlaces.get(i);

            summary.setPlaceName(place.getName());

            Contract contract = place.getContract();
            Meter meter = place.getMeter();


            if(Objects.nonNull(contract)){
                summary.setRenterName(contract.getRenter().getName());
                summary.setContractNumber(contract.getNumber());

                Account account = contract.getAccount();
                if(Objects.nonNull(account)){
                    summary.setAccountNumber(account.getNumber());

                    List<Payment> paymentList = account.getPaymentsList();
                    int summ = operations.getSumm(paymentList);
                    summary.setTotalPayments(summ);
                }
            }

            if(Objects.nonNull(meter)){
                summary.setMeterNumber(meter.getNumber());

                List<Reading> readingList = meter.getReadingsList();
                int max = operations.getMax(readingList);
                summary.setLastMeterData(max);
            }

            allSummaries.add(summary);
        }

        return allSummaries;
    }
}
