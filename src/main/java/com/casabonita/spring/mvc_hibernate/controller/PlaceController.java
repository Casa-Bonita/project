package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Place;
import com.casabonita.spring.mvc_hibernate.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String updatePlace(@RequestParam("plcId") Integer id, Model model){

        Place place = placeService.getPlaceById(id);
        model.addAttribute("place", place);

        return "place/place_info";
    }

    @RequestMapping(value = "/deletePlace", method = RequestMethod.GET)
    public String deletePlace(@RequestParam("plcId") Integer id){

        placeService.deletePlaceById(id);

        return "redirect:/places";
    }
}
