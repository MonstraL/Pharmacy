package com.pharmacy.controller;

import com.pharmacy.entity.PharmaciesMedications;
import com.pharmacy.service.PharmaciesMedicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pharmacies_medications")
public class PharmaciesMedicationsController {
    private PharmaciesMedicationsService pharmaciesMedicationsService;

    @Autowired
    public void setPharmaciesMedicationsService(PharmaciesMedicationsService pharmaciesMedicationsService) {
        this.pharmaciesMedicationsService = pharmaciesMedicationsService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Pharmacies controller";
    }

}
