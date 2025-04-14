package sof03.project.projectship.web;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sof03.project.projectship.domain.*;

@Controller
public class ShipFormController {

    private final ShipCaptainRepository shipCaptainRepository;

    @Autowired
    private CaptainRepository captainRepository;
    @Autowired
    private PortRepository portRepository;
    @Autowired
    private ShipTypeRepository shipTypeRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private FateRepository fateRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private VoyageEventRepositroy voyageEventRepository;

    ShipFormController(ShipCaptainRepository shipCaptainRepository) {
        this.shipCaptainRepository = shipCaptainRepository;
    }

    // SHIP FORM

    @GetMapping("/addshipform")
    public String addShipForm(Model model) {
        // model.addAttribute("captains", captainRepository.findAll());
        model.addAttribute("ports", portRepository.findAll());
        model.addAttribute("shipTypes", shipTypeRepository.findAll());
        model.addAttribute("owners", ownerRepository.findAll());
        model.addAttribute("fates", fateRepository.findAll());
        return "addship";
    }

    @PostMapping("/addShip")
    public String saveShip(
            @RequestParam String name,
            @RequestParam double displacement,
            @RequestParam double length,
            @RequestParam double beam,
            @RequestParam int yearBuilt,
            // @RequestParam Long captainId,
            @RequestParam Long portId,
            @RequestParam Long shipTypeId,
            @RequestParam Long ownerId,
            @RequestParam(required = false) Long fateId,
            // @RequestParam LocalDate startDate,
            // @RequestParam(required = false) LocalDate endDate,
            RedirectAttributes redirectAttributes) {

        // Create new Ship
        Ship ship = new Ship();
        ship.setShipName(name);
        ship.setDisplacement(displacement);
        ship.setLength(length);
        ship.setBeam(beam);
        ship.setYearBuilt(yearBuilt);

        // Set Port
        Port port = portRepository.findById(portId).orElse(null);
        if (port == null) {
            redirectAttributes.addAttribute("error", "Port not found.");
            return "redirect:/addShipForm";
        }
        ship.setPort(port);

        // Set Ship Type
        ShipType shipType = shipTypeRepository.findById(shipTypeId).orElse(null);
        if (shipType == null) {
            redirectAttributes.addAttribute("error", "Ship Type not found.");
            return "redirect:/addShipForm";
        }
        ship.setShipType(shipType);

        // Set Owner
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner == null) {
            redirectAttributes.addAttribute("error", "Owner not found.");
            return "redirect:/addShipForm";
        }
        ship.setOwner(owner);

        // Set Fate (Optional)
        Fate fate = (fateId != null) ? fateRepository.findById(fateId).orElse(null) : null;
        ship.setFate(fate);

        // Save the Ship
        shipRepository.save(ship);

        /* 
        // Retrieve the captain by Id
        Captain captain = captainRepository.findById(captainId).orElse(null);

        ShipCaptainId shipCaptainId = new ShipCaptainId(ship.getShipId(), captain.getCaptainId(), startDate);

        // Create ShipCaptain
        ShipCaptain shipCaptain = new ShipCaptain();
        shipCaptain.setId(shipCaptainId);
        shipCaptain.setShip(ship);
        shipCaptain.setCaptain(captain);
        
        // Save ShipCaptain
        shipCaptainRepository.save(shipCaptain);
         */

        return "redirect:/shiplist";
    }
       

    // CAPTAIN
/* 
    @GetMapping("/addcaptainform")
    public String addCaptainForm(@RequestHeader(value = "Referer", defaultValue = "/") String referer) {
        return "addcaptain";
    }

    @PostMapping("/addCaptain")
    public String saveCaptain(@RequestParam String name,
            @RequestParam int birthYear,
            @RequestParam String nationality, @RequestParam("referer") String referer) {
        Captain captain = new Captain(name, birthYear, nationality);
        captainRepository.save(captain);
       
        return "redirect:" + referer;
    }
   */ 
    // PORT

    @GetMapping("/addportform")
    public String addPortForm(Model model, @RequestHeader(value = "Referer", defaultValue = "/") String referer) {

        model.addAttribute("referer", referer);

        return "addPort";
    }

    @PostMapping("/addPort")
    public String savePort(@RequestParam String portName,
                            @RequestParam String portLocation,
                            @RequestParam("referer") String referer) {
        Port port = new Port(portName, portLocation);
        portRepository.save(port);
        
        return "redirect:" + referer;
    }

    // SHIP TYPE

    @GetMapping("/addshiptypeform")
    public String addShipTypeForm(Model model, @RequestHeader(value = "Referer", defaultValue = "/") String referer) {

        model.addAttribute("referer", referer);

        return "addshiptype";
    }

    @PostMapping("/addShipType")
    public String saveShipType(@RequestParam String name, @RequestParam("referer") String referer) {
        ShipType type = new ShipType(name);
        shipTypeRepository.save(type);
        
        return "redirect:" + referer;
    }

    // OWNER

    @GetMapping("/addownerform")
    public String addOwnerForm(Model model, @RequestHeader(value = "Referer", defaultValue = "/") String referer) {
        
        model.addAttribute("referer", referer);
        
        return "addowner";
    }

    @PostMapping("/addOwner")
    public String saveOwner(@RequestParam String name,
            @RequestParam String type,
            @RequestParam String country, @RequestParam("referer") String referer) {
        Owner owner = new Owner(name, type, country);
        ownerRepository.save(owner);
        
        return "redirect:" + referer;
    }

    // FATE

    @GetMapping("/addfateform")
    public String addFateForm(Model model, @RequestHeader(value = "Referer", defaultValue = "/") String referer) {

        model.addAttribute("referer", referer);

        return "addfate";
    }

    @PostMapping("/addFate")
    public String saveFate(@RequestParam String description, @RequestParam("referer") String referer) {
        Fate fate = new Fate(description);
        fateRepository.save(fate);

        return "redirect:" + referer;
    }

    // VOYAGE/EVENT

    @GetMapping("/addevent")
    public String showAddVoyageEventForm(Model model, @RequestHeader(value = "Referer", defaultValue = "/") String referer) {
        model.addAttribute("voyageEvent", new VoyageEvent());

        List<Ship> ships = (List<Ship>) shipRepository.findAll();
        model.addAttribute("ships", ships);

        model.addAttribute("referer", referer);

        return "addevent";
    }

    @PostMapping("/addvoyageevent")
    public String submitVoyageEventForm(@ModelAttribute("voyageEvent") VoyageEvent voyageEvent,
            @RequestParam("referer") String referer) {
        voyageEventRepository.save(voyageEvent);

        return "redirect:" + referer;
    }
}
