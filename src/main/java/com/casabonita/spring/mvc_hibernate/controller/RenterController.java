package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Renter;
import com.casabonita.spring.mvc_hibernate.service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RenterController {

    @Autowired
    private RenterService renterService;

    @RequestMapping("/renters")
    public String showAllRenters(Model model){

        List<Renter> allRenters = renterService.getAllRenters();
        model.addAttribute("allRenters", allRenters);

        return "renter/all_renters";
    }

    @RequestMapping("/addNewRenter")
    public String addNewRenter(Model model){

        Renter renter = new Renter();
        model.addAttribute("renter", renter);

        return "renter/renter_info";
    }

    @RequestMapping("/saveRenter")
    public String saveRenter(@ModelAttribute("renter") Renter renter){

        renterService.saveRenter(renter);

        return "redirect:/renters";
    }

    @RequestMapping("/updateRenter")
    public String updateRenter(@RequestParam("rentId") int id, Model model){

        Renter renter = renterService.getRenter(id);
        model.addAttribute("renter", renter);

        return "renter/renter_info";
    }

    @RequestMapping("/deleteRenter")
    public String deleteRenter(@RequestParam("rentId") int id){

        renterService.deleteRenter(id);

        return "redirect:/renters";
    }
}
