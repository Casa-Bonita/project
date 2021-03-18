package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.MeterData;
import com.casabonita.spring.mvc_hibernate.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @RequestMapping("/readings")
    public String showAllReadings(Model model){

        List<MeterData> allMeterDatas = readingService.getAllReadings();
        model.addAttribute("allReadings", allMeterDatas);

        return "reading/all_readings";
    }

    @RequestMapping("/addNewReading")
    public String addNewReading(Model model){

        MeterData meterData = new MeterData();
        model.addAttribute("reading", meterData);

        return "reading/reading_info";
    }

    @RequestMapping("/saveReading")
    public String saveReading(@ModelAttribute("contract") MeterData meterData){

        readingService.saveReading(meterData);

        return "redirect:/readings";
    }

    @RequestMapping("/updateReading")
    public String updateReading(@RequestParam("readId") int id, Model model){

        MeterData meterData = readingService.getReading(id);
        model.addAttribute("reading", meterData);

        return "reading/reading_info";
    }

    @RequestMapping("/deleteReading")
    public String deleteReading(@RequestParam("readId") int id){

        readingService.deleteReading(id);

        return "redirect:/readings";
    }
}
