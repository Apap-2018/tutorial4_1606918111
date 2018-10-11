package com.apap.apap_tutorial4.controller;

import javax.websocket.server.PathParam;

import com.apap.apap_tutorial4.model.*;
import com.apap.apap_tutorial4.service.FlightService;
import com.apap.apap_tutorial4.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PilotController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping("/")
    private String home() {
        return "home";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("pilot", new PilotModel());
        return "addPilot";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
    private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
        pilotService.addPilot(pilot);
        return "add";
    }

    @RequestMapping(value="/pilot/view", method = RequestMethod.GET)
    public String view(@RequestParam (value="licenseNumber") String licenseNumber, Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        model.addAttribute("pilot", pilot);
        return "view-pilot";
    }

    @RequestMapping(value="/pilot/delete/{licenseNumber}")
    public String deletePilot(@PathVariable(value="licenseNumber", required=true) String licenseNumber, Model model){
        String response = "Gagal menghapus data pilot";
            if (pilotService.removePilot(licenseNumber)){
            System.out.println("berhasil bos");
            response ="Berhasil menghapus data pilot";
        }
        model.addAttribute("response", response);
        return "deleteSuccess";
    }

    //redirect form
    @RequestMapping(value="/pilot/update/{licenseNumber}", method=RequestMethod.GET)
	public String update(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        model.addAttribute("pilot", pilotService.getPilotDetailByLicenseNumber(licenseNumber));
        return "updatePilot";

    }

    //success
    @RequestMapping(value="/pilot/update", method=RequestMethod.POST)
    private String updatePilotSubmit(@ModelAttribute PilotModel pilot){
        pilotService.updatePilot(pilot);
        return "updateSuccess";

    }
    



}