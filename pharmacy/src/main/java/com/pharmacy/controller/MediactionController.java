package com.pharmacy.controller;

import com.pharmacy.entity.Medication;
import com.pharmacy.entity.Pharmacy;
import com.pharmacy.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/medications")
public class MediactionController {
    private MedicationService medicationService;

    @Autowired
    public void setMedicationService(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Medications controller";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Medication getMedicationById(@PathVariable("id") int id) {
        return medicationService.findByIdInitialized(id);
    }

    @RequestMapping(value = "/by_substance/{id}+{nameSub}", method = RequestMethod.GET)
    @ResponseBody
    public List<Medication> getMedicationsBySubs(@PathVariable("id") int id, @PathVariable("nameSub") String nameSub) {
        return medicationService.findByActiveSubs(id, nameSub);
    }

    @RequestMapping(value = "/by_name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Medication> getMedicationsByName(@PathVariable("name") String name) {
        return medicationService.findMedicationsByName(name);
    }

}
