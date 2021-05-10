package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.Operations;
import com.casabonita.spring.mvc_hibernate.entity.*;
import com.casabonita.spring.mvc_hibernate.service.ContractService;
import com.casabonita.spring.mvc_hibernate.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StartController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartPage(Model model){

        Operations operations = new Operations();
        Place place;
        List<Place> allPlaces = placeService.getAllPlaces();

        List<Summary> allSummaries = new ArrayList<>();

        for (int i = 0; i < allPlaces.size(); i++) {

            Summary summary = new Summary();

            place = allPlaces.get(i);
            summary.setPlaceName(place.getName());

            Optional<Contract> contract = Optional.ofNullable(place.getContract());
            if(contract.isPresent()){
                summary.setContractNumber(place.getContract().getNumber());

                Optional<Renter> renter = Optional.ofNullable(place.getContract().getRenter());
                if(renter.isPresent()){
                    summary.setRenterName(place.getContract().getRenter().getName());
                }else{
                    summary.setRenterName(null);
                }

                Optional<Account> account = Optional.ofNullable(place.getContract().getAccount());
                if(account.isPresent()){
                    summary.setAccountNumber(place.getContract().getAccount().getNumber());

                    List<Payment> paymentList = place.getContract().getAccount().getPaymentsList();
                    int summ = operations.getSumm(paymentList);
                    summary.setTotalPayments(summ);
                }
                else{
                    summary.setAccountNumber(null);
                    summary.setTotalPayments(0);
                }

            }else{
                summary.setContractNumber(null);
                summary.setRenterName(null);
            }

            Optional<Meter> meter = Optional.ofNullable(place.getMeter());

            if(meter.isPresent()){
                summary.setMeterNumber(place.getMeter().getNumber());

                List<Reading> readingList = place.getMeter().getReadingsList();
                int max = operations.getMax(readingList);
                summary.setLastMeterData(max);

            }else{
                summary.setMeterNumber(0);
                summary.setLastMeterData(0);

            }

            allSummaries.add(summary);
        }

        model.addAttribute("summaryList", allSummaries);

        return "start_page";
    }

}
