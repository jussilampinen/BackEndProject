package sof03.project.projectship.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipId;

    // Non FK Atributes
    private String shipName;
    private double displacement;
    private double length;
    private double beam;
    private int yearBuilt;

    // FK Ship Type
    @ManyToOne
    @JoinColumn(name = "shipTypeId")
    private ShipType shipType;

    // FK Port
    @ManyToOne
    @JoinColumn(name = "portId")
    private Port port;

    // FK Owner
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

    // FK Fate
    @ManyToOne
    @JoinColumn(name = "fateId")
    private Fate fate;

    // FK Voyage or Event
    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    private List<VoyageEvent> events;

    // FK Ship-Captain
    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    private List<ShipCaptain> shipCaptain = new ArrayList<>();

    // Constructors
    public Ship() {
    }

    public Ship(String shipName, double displacement, double length, double beam, int yearBuilt,
            ShipType shipType, Port port, Owner owner, Fate fate, List<VoyageEvent> events,
            List<ShipCaptain> shipCaptain) {
        this.shipName = shipName;
        this.displacement = displacement;
        this.length = length;
        this.beam = beam;
        this.yearBuilt = yearBuilt;
        this.shipType = shipType;
        this.port = port;
        this.owner = owner;
        this.fate = fate;
        this.events = events;
        this.shipCaptain = shipCaptain;
    }

    // Getters & Setters

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBeam() {
        return beam;
    }

    public void setBeam(double beam) {
        this.beam = beam;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Fate getFate() {
        return fate;
    }

    public void setFate(Fate fate) {
        this.fate = fate;
    }

    public List<VoyageEvent> getEvents() {
        return events;
    }

    public void setEvents(List<VoyageEvent> events) {
        this.events = events;
    }

    public List<ShipCaptain> getShipCaptain() {
        return shipCaptain;
    }

    public void setShipCaptain(List<ShipCaptain> shipCaptain) {
        this.shipCaptain = shipCaptain;
    }

}
