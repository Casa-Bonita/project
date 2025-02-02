package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Reading;
import com.casabonita.spring.mvc_hibernate.service.MeterService;
import com.casabonita.spring.mvc_hibernate.service.ReadingService;
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
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @Autowired
    private MeterService meterService;

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
    public String saveReading(@RequestParam("meter.number") String meterNumber, @ModelAttribute("reading") Reading reading){

        readingService.saveReading(reading, meterNumber);

        return "redirect:/readings";
    }

    @RequestMapping(value = "/updateReading", method = RequestMethod.GET)
    public String updateReading(@RequestParam("readId") Integer id, Model model){

        Reading reading = readingService.getReading(id);
        model.addAttribute("reading", reading);

        return "reading/reading_info";
    }

    @RequestMapping(value = "/deleteReading", method = RequestMethod.GET)
    public String deleteReading(@RequestParam("readId") Integer id){

        readingService.deleteReadingById(id);

        return "redirect:/readings";
    }

    // Отображение перечня отсортированных по возрастанию Meter-ов
    @ModelAttribute("meterMap")
    public TreeMap<String, String> getSortedMeterMap() {

        Map<String, String> meterMap = new HashMap<>();

        List<Meter> meterList = meterService.getAllMeters();

        for (int i = 0; i < meterList.size(); i++) {
            String meterNumber = meterList.get(i).getNumber();
            meterMap.put(meterNumber, meterNumber);
        }
        // sort HahsMap by TreeMap
        TreeMap<String, String> sortedMeterMap = new TreeMap<>();
        sortedMeterMap.putAll(meterMap);

        return sortedMeterMap;
    }
}
