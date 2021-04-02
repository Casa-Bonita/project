package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Payment;
import com.casabonita.spring.mvc_hibernate.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

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
    public String savePayment(@ModelAttribute("payment") Payment payment){

        paymentService.savePayment(payment);

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

        paymentService.deletePaymetn(id);

        return "redirect:/payments";
    }
}
