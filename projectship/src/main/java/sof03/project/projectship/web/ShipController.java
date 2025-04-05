package sof03.project.projectship.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sof03.project.projectship.domain.ShipRepository;

@Controller
public class ShipController {

    @Autowired
    private ShipRepository shipRepository;
    
    @GetMapping("/shiplist")
    public String shiplist(Model model){
        model.addAttribute("ships", shipRepository.findAll());
        return "shiplist";
    }
}


