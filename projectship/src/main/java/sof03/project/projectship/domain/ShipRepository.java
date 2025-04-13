package sof03.project.projectship.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShipRepository extends CrudRepository<Ship, Long> {
    List<Ship> findByShipName(String shipName);
}
