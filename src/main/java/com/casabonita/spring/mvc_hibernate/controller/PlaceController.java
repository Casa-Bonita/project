package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.entity.MeterData;
import com.casabonita.spring.mvc_hibernate.service.PlaceService;
import com.casabonita.spring.mvc_hibernate.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ReadingService readingService;

    @RequestMapping("/places")
    public String showAllPlaces(Model model){

        List<Place> allPlaces =  placeService.getAllPlaces();
        model.addAttribute("allPlaces", allPlaces);

        List<MeterData> allMeterDatas = readingService.getAllReadings();

        return "place/all_places";
    }

    @RequestMapping("/addNewPlace")
    public String addNewPlace(Model model){

        Place place = new Place();
        model.addAttribute("place", place);

        return "place/place_info";
    }

    @RequestMapping("/savePlace")
    public String savePlace(@ModelAttribute("place") Place place){

        placeService.savePlace(place);

        return "redirect:/places";
    }

    @RequestMapping("/updatePlace")
    public String updatePlace(@RequestParam("plId") int id, Model model){

        Place place = placeService.getPlace(id);
        model.addAttribute("place", place);

        return "place/place_info";
    }

    @RequestMapping("/deletePlace")
    public String deletePlace(@RequestParam("plId") int id){

        placeService.deletePlace(id);

        return "redirect:/places";
    }

}
