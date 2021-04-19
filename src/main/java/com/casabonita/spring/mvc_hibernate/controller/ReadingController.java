package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Reading;
import com.casabonita.spring.mvc_hibernate.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @RequestMapping(value = "/readings", method = RequestMethod.GET)
    public String showAllReadings(Model model){

        List<Reading> allReadings = readingService.getAllReadings();
        model.addAttribute("readingsList", allReadings);

        return "reading/all_readings";
    }

    @RequestMapping(value = "/addNewReading", method = RequestMethod.GET)
    public String addNewReading(Model model){

        Reading reading = new Reading();
        model.addAttribute("reading", reading);

        return "reading/reading_info";
    }

    @RequestMapping(value = "/saveReading", method = RequestMethod.POST)
    public String saveReading(@ModelAttribute("meterData") Reading reading){

        readingService.saveReading(reading);

        return "redirect:/readings";
    }

    @RequestMapping(value = "/updateReading", method = RequestMethod.GET)
    public String updateReading(@RequestParam("readId") int id, Model model){

        Reading reading = readingService.getReading(id);
        model.addAttribute("reading", reading);

        return "reading/reading_info";
    }

    @RequestMapping(value = "/deleteReading", method = RequestMethod.GET)
    public String deleteReading(@RequestParam("readId") int id){

        readingService.deleteReading(id);

        return "redirect:/readings";
    }
}
