package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
}
