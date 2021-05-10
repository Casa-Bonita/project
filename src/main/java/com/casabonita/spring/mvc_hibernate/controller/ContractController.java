package com.casabonita.spring.mvc_hibernate.controller;

import com.casabonita.spring.mvc_hibernate.entity.*;
import com.casabonita.spring.mvc_hibernate.service.ContractService;
import com.casabonita.spring.mvc_hibernate.service.PlaceService;
import com.casabonita.spring.mvc_hibernate.service.RenterService;
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
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private RenterService renterService;

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public String showAllContracts(Model model){

        List<Contract> allContracts = contractService.getAllContracts();
        model.addAttribute("contractsList", allContracts);

        return "contract/all_contracts";
    }

    @RequestMapping(value = "/addNewContract", method = RequestMethod.GET)
    public String addNewContract(Model model){

        Contract contract = new Contract();
        model.addAttribute("contract", contract);

        return "contract/contract_info";
    }

    @RequestMapping(value = "/saveContract", method = RequestMethod.POST)
    public String saveContract(@RequestParam("contractPlace.number") int contractPlaceNumber,
                               @RequestParam("renter.name") String renterName,
                               @ModelAttribute("contract") Contract contract){

        Place place = placeService.getPlaceByNumber(contractPlaceNumber);

        contract.setContractPlace(place);

        Renter renter = renterService.getRenterByName(renterName);

        contract.setRenter(renter);

        contractService.saveContract(contract);

        return "redirect:/contracts";
    }

    @RequestMapping(value = "/updateContract", method = RequestMethod.GET)
    public String updateContract(@RequestParam("contrId") int id, Model model){

        Contract contract = contractService.getContract(id);
        model.addAttribute("contract", contract);

        return "contract/contract_info";
    }

    @RequestMapping(value = "/deleteContract", method = RequestMethod.GET)
    public String deleteContract(@RequestParam("contrId") int id){

        contractService.deleteContract(id);

        return "redirect:/contracts";
    }

    // Отображение перечня отсортированных по возрастанию Renter-ов
    @ModelAttribute("renterMap")
    public TreeMap<String, String> getSortedRenterMap() {

        Map<String, String> renterMap = new HashMap<>();

        List<Renter> renterList = renterService.getAllRenters();

        for (int i = 0; i < renterList.size(); i++) {
            String renterName = renterList.get(i).getName();
            renterMap.put(renterName, renterName);
        }
        // sort HahsMap by TreeMap
        TreeMap<String, String> sortedRenterMap = new TreeMap<>();
        sortedRenterMap.putAll(renterMap);

        return sortedRenterMap;
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
