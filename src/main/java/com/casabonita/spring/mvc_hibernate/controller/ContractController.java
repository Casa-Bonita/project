package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.MeterData;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import com.casabonita.spring.mvc_hibernate.entity.Summary;
import com.casabonita.spring.mvc_hibernate.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContractController {

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public String showAllContracts(Model model){

        List<Contract> allContracts = contractService.getAllContracts();
        model.addAttribute("contractsList", allContracts);

        return "contract/all_contracts";
    }

    @RequestMapping(value = "/addNewContract", method = RequestMethod.GET)
    public String addNewContract(Model model){

        Contract contract = new Contract();
        model.addAttribute("contract", contract);

        return "contract/contract_info";
    }

    @RequestMapping(value = "/saveContract", method = RequestMethod.POST)
    public String saveContract(@ModelAttribute("contract") Contract contract){

        contractService.saveContract(contract);

        return "redirect:/contracts";

    }

    @RequestMapping(value = "/updateContract", method = RequestMethod.GET)
    public String updateContract(@RequestParam("contrId") int id, Model model){

        Contract contract = contractService.getContract(id);
        model.addAttribute("contract", contract);

        return "contract/contract_info";
    }

    @RequestMapping(value = "/deleteContract", method = RequestMethod.GET)
    public String deleteContract(@RequestParam("contrId") int id){

        contractService.deleteContract(id);

        return "redirect:/contracts";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartPage(Model model){

        Contract contract;
        List<Contract> allContracts = contractService.getAllContracts();

        List<Summary> allSummaries = new ArrayList<>();

        Summary summary = new Summary();

        for (int i = 0; i < allContracts.size(); i++) {
            contract = allContracts.get(i);
            summary.setPlaceName(contract.getContractPlace().getName());
            summary.setRenterName(contract.getRenter().getName());
            summary.setContractNumber(contract.getNumber());
            summary.setContractFinishDate(contract.getFinishDate());
            summary.setMeterNumber(contract.getContractPlace().getMeter().getNumber());

            int max = 0;
            List<MeterData> meterDataList = contract.getContractPlace().getMeter().getMeterDatasList();
            for (int j = 0; j < meterDataList.size(); j++) {
                if(max < meterDataList.get(j).getTransferData()){
                    max = meterDataList.get(j).getTransferData();
                }
            }
            summary.setLastMeterData(max);

            summary.setAccountNumber(contract.getAccount().getNumber());

            int total = 0;
            List<Payment> accountDatasList = contract.getAccount().getPaymentsList();
            for (int j = 0; j < accountDatasList.size(); j++) {
                total = total + accountDatasList.get(j).getAmount();
            }

            summary.setTotalPayments(total);

            allSummaries.add(summary);
        }

        model.addAttribute("summaryList", allSummaries);

        return "start_page";
    }
}
