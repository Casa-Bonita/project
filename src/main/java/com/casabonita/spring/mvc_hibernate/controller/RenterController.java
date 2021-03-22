package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.Renter;
import com.casabonita.spring.mvc_hibernate.service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String saveRenter(@Valid @ModelAttribute("renter") Renter renter, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "renter/renter_info";
        }
        else{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try{
                String dateConvert = renter.getDateStringFormat();
                Date date = sdf.parse(dateConvert);

                Renter newRenter = new Renter(renter.getRenterName(), renter.getRenterOGRN(), renter.getRenterINN(), date,
                        renter.getRenterAddress(), renter.getRenterDirector(), renter.getRenterContactName(), renter.getRenterPhone(),
                        renter.getRenterContract());
                renterService.saveRenter(newRenter);
            }
            catch(ParseException ex){
                ex.printStackTrace();
            }

            return "redirect:/renters";
        }
    }

    @RequestMapping("/updateRenter")
    public String updateRenter(@RequestParam("rentId") int id, Model model){

        Renter renter = renterService.getRenter(id);
        model.addAttribute("renter", renter);

        return "renter/renter_update";
    }

    @RequestMapping("/deleteRenter")
    public String deleteRenter(@RequestParam("rentId") int id){

        renterService.deleteRenter(id);

        return "redirect:/renters";
    }
}
