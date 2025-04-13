package sof03.project.projectship.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sof03.project.projectship.domain.FateRepository;
import sof03.project.projectship.domain.OwnerRepository;
import sof03.project.projectship.domain.PortRepository;
import sof03.project.projectship.domain.Ship;
import sof03.project.projectship.domain.ShipCaptainRepository;
import sof03.project.projectship.domain.ShipRepository;
import sof03.project.projectship.domain.ShipTypeRepository;

@Controller
public class ShipController {

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private ShipTypeRepository shipTypeRepository;
    @Autowired
    private PortRepository portRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private FateRepository fateRepository;
    // @Autowired
    // private ShipCaptainRepository shipCaptainRepository;

    @GetMapping("/shiplist")
    public String shiplist(Model model) {
        model.addAttribute("ships", shipRepository.findAll());
        return "shiplist";
    }

    @GetMapping("/edit/{id}")
    public String updateShip(@PathVariable("id") Long id, Model model) {
        Optional<Ship> ship = shipRepository.findById(id);
        model.addAttribute("ship", ship.get());

        model.addAttribute("shipTypes", shipTypeRepository.findAll());
        model.addAttribute("ports", portRepository.findAll());
        model.addAttribute("owners", ownerRepository.findAll());
        model.addAttribute("fates", fateRepository.findAll());
        // model.addAttribute("captains", shipCaptainRepository.findAll());
        return "editship";
    }

    @PostMapping("/edit")
    public String saveEditedShip(@ModelAttribute("ship") Ship ship) {
        if (ship.getShipType() != null && ship.getShipType().getShipTypeId() != null) {
            ship.setShipType(shipTypeRepository.findById(ship.getShipType().getShipTypeId()).orElse(null));
        }
        if (ship.getPort() != null && ship.getPort().getPortId() != null) {
            ship.setPort(portRepository.findById(ship.getPort().getPortId()).orElse(null));
        }
        if (ship.getOwner() != null && ship.getOwner().getOwnerId() != null) {
            ship.setOwner(ownerRepository.findById(ship.getOwner().getOwnerId()).orElse(null));
        }
        if (ship.getFate() != null && ship.getFate().getFateId() != null) {
            ship.setFate(fateRepository.findById(ship.getFate().getFateId()).orElse(null));
        }

        shipRepository.save(ship);
        return "redirect:/shiplist";
    }

    @GetMapping("/delete/{id}")
    public String deleteShip(@PathVariable("id") Long id) {
        shipRepository.deleteById(id);
        return "redirect:/shiplist";
    }
}
