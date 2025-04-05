package sof03.project.projectship.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Captain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long captainId;

    private String name;
    private int birthYear;
    private String nationality;

    @OneToMany(mappedBy = "captain", cascade = CascadeType.ALL)
    private List<ShipCaptain> shipAssignments = new ArrayList<>();

    public Captain() {}

    public Captain(String name, int birthYear, String nationality) {
        this.name = name;
        this.birthYear = birthYear;
        this.nationality = nationality;
    }

    // Getters and setters
    public Long getCaptainId() {
        return captainId;
    }

    public void setCaptainId(Long captainId) {
        this.captainId = captainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<ShipCaptain> getShipAssignments() {
        return shipAssignments;
    }

    public void setShipAssignments(List<ShipCaptain> shipAssignments) {
        this.shipAssignments = shipAssignments;
    }
}

