package com.pharmacy.controller;

import com.pharmacy.entity.Pharmacy;
import com.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pharmacies")
public class PharmacyController {
    private PharmacyService pharmacyService;

    @Autowired
    public void setPharmacyService(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Pharmacies controller";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Pharmacy getPharmacyById(@PathVariable("id") int id) {
        return pharmacyService.findByIdInitialized(id);
    }

    @RequestMapping(value = "/by_location/{id}+{longitude}+{latitude}", method = RequestMethod.GET)
    @ResponseBody
    public List getMedicationPharmaciesByLocation(@PathVariable("id") int id, @PathVariable("longitude") float longitude,
                                                  @PathVariable("latitude") float latitude) {
        return  pharmacyService.findByLocation(id, longitude, latitude);
    }

    @RequestMapping(value = "/by_price/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List getMedicationPharmaciesByPrice(@PathVariable("id") int id) {
        return pharmacyService.findPharmacyAndPrice(id);
    }
}
