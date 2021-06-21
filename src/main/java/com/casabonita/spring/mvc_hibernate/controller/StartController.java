package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Summary;
import com.casabonita.spring.mvc_hibernate.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StartController {

    @Autowired
    private StartService startService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartPage(Model model){

        List<Summary> allSummaries = startService.getAllSummaries();

        model.addAttribute("summaryList", allSummaries);

        return "start_page";
    }
}
