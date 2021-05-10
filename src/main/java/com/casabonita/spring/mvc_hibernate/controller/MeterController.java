package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Meter;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.service.MeterService;
import com.casabonita.spring.mvc_hibernate.service.PlaceService;
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
public class MeterController {

    @Autowired
    private MeterService meterService;

    @Autowired
    private PlaceService placeService;

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
    public String saveMeter(@RequestParam("meterPlace.number") int meterPlaceNumber, @ModelAttribute("meter") Meter meter){

        Place place = placeService.getPlaceByNumber(meterPlaceNumber);

        meter.setMeterPlace(place);

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

    // Отображение перечня отсортированных по возрастанию Place-ов
    @ModelAttribute("placeMap")
    public TreeMap<Integer, Integer> getSortedPlaceMap() {

        Map<Integer, Integer> placeMap = new HashMap<>();

        List<Place> placeList = placeService.getAllPlaces();

        for (int i = 0; i < placeList.size(); i++) {
            Integer placeNumber = placeList.get(i).getNumber();
            placeMap.put(placeNumber, placeNumber);
        }
        // sort HahsMap by TreeMap
        TreeMap<Integer, Integer> sortedPlaceMap = new TreeMap<>();
        sortedPlaceMap.putAll(placeMap);

        return sortedPlaceMap;
    }
}
