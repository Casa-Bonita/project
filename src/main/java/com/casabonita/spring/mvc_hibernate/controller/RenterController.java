package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Renter;
import com.casabonita.spring.mvc_hibernate.service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RenterController {

    @Autowired
    private RenterService renterService;

    @RequestMapping(value = "/renters", method = RequestMethod.GET)
    public String showAllRenters(Model model){

        List<Renter> allRenters = renterService.getAllRenters();
        model.addAttribute("rentersList", allRenters);

        return "renter/all_renters";
    }

    @RequestMapping(value = "/addNewRenter", method = RequestMethod.GET)
    public String addNewRenter(Model model){

        Renter renter = new Renter();
        model.addAttribute("renter", renter);

        return "renter/renter_info";
    }

    @RequestMapping(value = "/saveRenter", method = RequestMethod.POST)
    public String saveRenter(@ModelAttribute("renter") Renter renter) {

        renterService.saveRenter(renter);

        return "redirect:/renters";

    }

//    @RequestMapping(value = "/saveRenter", method = RequestMethod.POST)
//    public String saveRenter(@Valid @ModelAttribute("renter") Renter renter, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//
//            return "renter/renter_info";
//        }
//        else{
//
//            renterService.saveRenter(renter);
//
//            return "redirect:/renters";
//        }
//    }

    @RequestMapping(value = "/updateRenter", method = RequestMethod.GET)
    public String updateRenter(@RequestParam("rentId") int id, Model model){

        Renter renter = renterService.getRenter(id);
        model.addAttribute("renter", renter);

        return "renter/renter_info";
    }

    @RequestMapping(value = "/deleteRenter", method = RequestMethod.GET)
    public String deleteRenter(@RequestParam("rentId") int id){

        renterService.deleteRenter(id);

        return "redirect:/renters";
    }
}
