package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Account;
import com.casabonita.spring.mvc_hibernate.entity.Payment;
import com.casabonita.spring.mvc_hibernate.service.AccountService;
import com.casabonita.spring.mvc_hibernate.service.PaymentService;
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
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String showAllPayments(Model model){

        List<Payment> allPayments = paymentService.getAllPayments();
        model.addAttribute("paymentsList", allPayments);

        return "payment/all_payments";
    }

    @RequestMapping(value = "/addNewPayment", method = RequestMethod.GET)
    public String addNewPayment(Model model){

        Payment payment = new Payment();
        model.addAttribute("payment", payment);

        return "payment/payment_info";
    }

    @RequestMapping(value = "/savePayment", method = RequestMethod.POST)
    public String savePayment(@RequestParam("account.number") String accountNumber, @ModelAttribute("payment") Payment payment){

        paymentService.savePayment(payment, accountNumber);

        return "redirect:/payments";
    }

    @RequestMapping(value = "/updatePayment", method = RequestMethod.GET)
    public String updatePayment(@RequestParam("paymId") int id, Model model){

        Payment payment = paymentService.getPayment(id);
        model.addAttribute("payment", payment);

        return "payment/payment_info";
    }

    @RequestMapping(value = "/deletePayment", method = RequestMethod.GET)
    public String deletePayment(@RequestParam("paymId") int id){

        paymentService.deletePayment(id);

        return "redirect:/payments";
    }

    // Отображение перечня отсортированных по возрастанию Account-ов
    @ModelAttribute("accountMap")
    public TreeMap<String, String> getSortedAccountMap() {

        Map<String, String> accountMap = new HashMap<>();

        List<Account> accountList = accountService.getAllAccounts();

        for (int i = 0; i < accountList.size(); i++) {
            String accountNumber = accountList.get(i).getNumber();
            accountMap.put(accountNumber, accountNumber);
        }
        // sort HahsMap by TreeMap
        TreeMap<String, String> sortedAccountMap = new TreeMap<>();
        sortedAccountMap.putAll(accountMap);

        return sortedAccountMap;
    }

}
