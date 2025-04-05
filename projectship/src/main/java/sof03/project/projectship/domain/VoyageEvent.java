package sof03.project.projectship.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Voyage")
public class VoyageEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

 
    private String eventType; 
    private String location;
    private LocalDate date;
    private String description;

    // Constructors
    public VoyageEvent() {}

    public VoyageEvent(Ship ship, String eventType, String location, LocalDate date, String description) {
        this.ship = ship;
        this.eventType = eventType;
        this.location = location;
        this.date = date;
        this.description = description;
    }

    // Getters and setters
    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
