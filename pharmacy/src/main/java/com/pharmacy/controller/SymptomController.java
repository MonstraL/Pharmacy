package com.pharmacy.controller;

import com.pharmacy.entity.Symptom;
import com.pharmacy.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pharmacies")
public class SymptomController {
    private SymptomService symptomService;

    @Autowired
    public void setSymptomService(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Pharmacies controller";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Symptom getPharmacyById(@PathVariable("id") int id) {
        return symptomService.findByIdInitialized(id);
    }
}
