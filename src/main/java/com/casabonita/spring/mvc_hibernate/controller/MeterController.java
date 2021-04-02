package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MeterController {

    @Autowired
    private MeterService meterService;

    @RequestMapping(value = "/meters", method = RequestMethod.GET)
    public String showAllMeters(Model model){

        List<Meter> allMeters = meterService.getAllMeters();
        model.addAttribute("metersList", allMeters);

        return "meter/all_meters";
    }

    @RequestMapping(value = "/addNewMeter", method = RequestMethod.GET)
    public String addNewMeter(Model model){

        Meter meter = new Meter();
        model.addAttribute("meter", meter);

        return "meter/meter_info";
    }

    @RequestMapping(value = "/saveMeter", method = RequestMethod.POST)
    public String saveMeter(@ModelAttribute("meter") Meter meter){

        meterService.saveMeter(meter);
        return "redirect:/meters";

    }

    @RequestMapping(value = "/updateMeter", method = RequestMethod.GET)
    public String updateMeter(@RequestParam("metId") int id, Model model){

        Meter meter = meterService.getMeter(id);
        model.addAttribute("meter", meter);

        return "meter/meter_info";
    }

    @RequestMapping(value = "/deleteMeter", method = RequestMethod.GET)
    public String deleteMeter(@RequestParam("metId") int id){

        meterService.deleteMeter(id);

        return "redirect:/meters";
    }
}
