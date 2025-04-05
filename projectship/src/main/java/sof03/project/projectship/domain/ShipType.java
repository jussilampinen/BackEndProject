package sof03.project.projectship.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class ShipType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipTypeId;

    private String shipType;

    @OneToMany(mappedBy = "shipType", cascade = CascadeType.ALL)
    private List<Ship> ships;

    // Constructors
    public ShipType(){}

    public ShipType(String shipType) {
        this.shipType = shipType;
    }
   
    // Getters & Setters

    public Long getShipTypeId() {
        return shipTypeId;
    }

    public void setShipTypeId(Long shipTypeId) {
        this.shipTypeId = shipTypeId;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    
}
