package com.apap.apap_tutorial4.controller;

import com.apap.apap_tutorial4.model.FlightModel;
import com.apap.apap_tutorial4.model.PilotModel;
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
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model ) {
        FlightModel flight = new FlightModel();
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flight.setPilot(pilot);
        model.addAttribute("flight", flight);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add", method = RequestMethod.POST, params={"addRow"})
    private String addFlightSubmit(@ModelAttribute FlightModel flight) {
        flightService.addFlight(flight);
        return "add";
    }
    // @RequestMapping(value="/flight/add", method=RequestMethod.POST, params={"addRow"})
    // public String addRow(@RequestParam(value="flightNumber") String flightNumber, @ModelAttribute PilotModel pilot, Model model){
    //     pilot.getPilotFlight().add(new FlightModel());
    //     return "addFlight";
    // }

    @RequestMapping(value="/flight/delete", method=RequestMethod.POST)
    public String deleteFlight(@ModelAttribute PilotModel pilot, Model model){
        String response ="Berhasil menghapus data";
        for(FlightModel flight: pilot.getPilotFlight()){
            flightService.removeFlight(flight.getFlightNumber());
        } 
        model.addAttribute("response", response);
        return "deleteSuccess";
    }

    @RequestMapping(value= "/flight/view", method = RequestMethod.GET)
    private String viewFlight(@RequestParam(value="flightNumber") String flightNumber, Model model){
        FlightModel flight= flightService.getFlightDetailByFlightNumber(flightNumber);
        model.addAttribute("flight", flight);
        return "view-flight";
    }

    //redirect to form
    @RequestMapping(value="/flight/update", method=RequestMethod.GET)
    public String updateFlight(@RequestParam(value="flightNumber") String flightNumber, Model model){
        model.addAttribute("flight", flightService.getFlightDetailByFlightNumber(flightNumber));
        return "updateFlight";
    }

    //success
    @RequestMapping(value="/flight/update", method=RequestMethod.POST)
    private String updateFlightSuccess(@ModelAttribute FlightModel flight){
        flightService.updateFlight(flight);
        return "updateSuccess";

    }


}