package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.Operations;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import com.casabonita.spring.mvc_hibernate.entity.Reading;
import com.casabonita.spring.mvc_hibernate.entity.Summary;
import com.casabonita.spring.mvc_hibernate.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StartController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartPage(Model model) throws ParseException {

        Operations operations = new Operations();
        Contract contract;
        List<Contract> allContracts = contractService.getAllContracts();

        List<Summary> allSummaries = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < allContracts.size(); i++) {

            Summary summary = new Summary();

            contract = allContracts.get(i);
            summary.setPlaceName(contract.getContractPlace().getName());
            summary.setRenterName(contract.getRenter().getName());
            summary.setContractNumber(contract.getNumber());
            summary.setMeterNumber(contract.getContractPlace().getMeter().getNumber());

            List<Reading> readingList = contract.getContractPlace().getMeter().getReadingsList();
            int max = operations.getMax(readingList);
            summary.setLastMeterData(max);

            summary.setAccountNumber(contract.getAccount().getNumber());

            List<Payment> paymentList = contract.getAccount().getPaymentsList();
            int summ = operations.getSumm(paymentList);
            summary.setTotalPayments(summ);

            allSummaries.add(summary);
        }

        model.addAttribute("summaryList", allSummaries);



        return "start_page";
    }

}
