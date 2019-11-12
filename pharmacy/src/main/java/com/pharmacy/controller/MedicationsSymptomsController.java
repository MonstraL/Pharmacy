package com.pharmacy.controller;

import com.pharmacy.service.MedicationsSymptomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/medications_symptoms")
public class MedicationsSymptomsController {
    private MedicationsSymptomsService medicationsSymptomsService;

    @Autowired
    public void setMedicationsSymptomsService(MedicationsSymptomsService medicationsSymptomsService) {
        this.medicationsSymptomsService = medicationsSymptomsService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "MedicationsSymptoms controller";
    }

}
