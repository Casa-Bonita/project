package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

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
    public String saveAccount(@ModelAttribute("account") Account account){

        accountService.saveAccount(account);
        return "redirect:/accounts";

    }

    @RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
    public String updateAccount(@RequestParam("accId") int id, Model model){

        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);

        return "account/account_info";
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(@RequestParam("accId") int id){

        accountService.deleteAccount(id);

        return "redirect:/accounts";
    }
}
