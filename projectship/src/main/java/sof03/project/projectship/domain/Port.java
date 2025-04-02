package sof03.project.projectship.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long portId;

    private String portName;
    private String portLocation;

    @OneToMany(mappedBy = "shipType", cascade = CascadeType.ALL)
    private List<Ship> ships;

    public Port(){}

    public Port(String portName, String portLocation) {
        this.portName = portName;
        this.portLocation = portLocation;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPortLocation() {
        return portLocation;
    }

    public void setPortLocation(String portLocation) {
        this.portLocation = portLocation;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    
}
