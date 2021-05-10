package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Contract;
import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.service.ContractService;
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
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/places", method = RequestMethod.GET)
    public String showAllPlaces(Model model){

        List<Place> allPlaces =  placeService.getAllPlaces();
        model.addAttribute("placesList", allPlaces);

        return "place/all_places";
    }

    @RequestMapping(value = "/addNewPlace", method = RequestMethod.GET)
    public String addNewPlace(Model model){

        Place place = new Place();
        model.addAttribute("place", place);

        return "place/place_info";
    }

    @RequestMapping(value = "/savePlace", method = RequestMethod.POST)
    public String savePlace(@ModelAttribute("place") Place place){

        placeService.savePlace(place);

        return "redirect:/places";
    }

    @RequestMapping(value = "/updatePlace", method = RequestMethod.GET)
    public String updatePlace(@RequestParam("plcId") int id, Model model){

        Place place = placeService.getPlace(id);
        model.addAttribute("place", place);

        return "place/place_info";
    }

    @RequestMapping(value = "/deletePlace", method = RequestMethod.GET)
    public String deletePlace(@RequestParam("plcId") int id){

        placeService.deletePlace(id);

        return "redirect:/places";
    }
}
