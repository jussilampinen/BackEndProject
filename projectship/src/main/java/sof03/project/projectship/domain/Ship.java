package sof03.project.projectship.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    

    
}
