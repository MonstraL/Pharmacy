package com.pharmacy.controller;

import com.pharmacy.entity.Chain;
import com.pharmacy.service.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chains")
public class ChainController {
    private ChainService chainService;

    @Autowired
    public void setChainService(ChainService chainService) {
        this.chainService = chainService;
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String displayInfo(){
        return "Medications controller";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Chain getMedicationById(@PathVariable("id") int id) {
        return chainService.findByIdInitialized(id);
    }
}
