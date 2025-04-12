package sof03.project.projectship.domain;

import java.time.LocalDate;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "ship_captain")
public class ShipCaptain {
    @EmbeddedId
    private ShipCaptainId id;

    @ManyToOne
    @MapsId("shipId")
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    @ManyToOne
    @MapsId("captainId")
    @JoinColumn(name = "captain_id", nullable = false)
    private Captain captain;

    @Column(name = "start_date", insertable = false, updatable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    public ShipCaptain() {
    }

    public ShipCaptain(Ship ship, Captain captain, LocalDate startDate, LocalDate endDate) {
        this.ship = ship;
        this.captain = captain;
        this.startDate = startDate;
        this.endDate = endDate;
        this.id = new ShipCaptainId(ship.getShipId(), captain.getCaptainId(), startDate);
    }

    public ShipCaptainId getId() {
        return id;
    }

    public void setId(ShipCaptainId id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}