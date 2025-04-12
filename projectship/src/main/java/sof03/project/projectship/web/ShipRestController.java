package sof03.project.projectship.web;

import sof03.project.projectship.domain.Ship;
import sof03.project.projectship.domain.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/ships")
public class ShipRestController {

    @Autowired
    private ShipRepository shipRepository;

    @GetMapping
    public @ResponseBody List<Ship> getAllShips() {
        return (List<Ship>) shipRepository.findAll();
    }

    @GetMapping("/ships/{id}")
    public @ResponseBody Optional<Ship> getShip(@PathVariable("id") Long id) {
        return shipRepository.findById(id);
    }

    @PostMapping("/ships")
    public @ResponseBody Ship saveShip(@RequestBody Ship ship) {
        return shipRepository.save(ship);
    }
}
