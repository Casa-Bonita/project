package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.service.AccountService;
import com.casabonita.spring.mvc_hibernate.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String showAllAccounts(Model model){

        List<Account> allAccounts = accountService.getAllAccounts();
        model.addAttribute("accountsList", allAccounts);

        return "account/all_accounts";
    }

    @RequestMapping(value = "/addNewAccount", method = RequestMethod.GET)
    public String addNewAccount(Model model){

        Account account = new Account();
        model.addAttribute("account", account);

        return "account/account_info";
    }

    @RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
    public String saveAccount(@RequestParam("accountContract.number") String accountContractNumber,
                              @ModelAttribute("account") Account account){

        accountService.saveAccount(account, accountContractNumber);

        return "redirect:/accounts";

    }

    @RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
    public String updateAccount(@RequestParam("accId") Integer id, Model model){

        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);

        return "account/account_info";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(@RequestParam("accId") Integer id){

        accountService.deleteAccountById(id);

        return "redirect:/accounts";
    }

    // Отображение перечня отсортированных по возрастанию "свободных" Contract-ов (без Account-ов)
    @ModelAttribute("contractMap")
    public TreeMap<String, String> getSortedContractMap() {

        Map<String, String> contractMap = new HashMap<>();

        List<Contract> contractList = contractService.getAllContracts();

        for (int i = 0; i < contractList.size(); i++) {
            if(contractList.get(i).getAccount() == null){
                String contractNumber = contractList.get(i).getNumber();
                contractMap.put(contractNumber, contractNumber);
            }
        }
        // sort HahsMap by TreeMap
        TreeMap<String, String> sortedContractMap = new TreeMap<>();
        sortedContractMap.putAll(contractMap);

        return sortedContractMap;
    }
}
