package sof03.project.projectship.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ShipCaptainId implements Serializable {

    @Column(name = "ship_id")
    private Long shipId;

    @Column(name = "captain_id")
    private Long captainId;

    @Column(name = "start_date")
    private LocalDate startDate;

    public ShipCaptainId() {
    }

    public ShipCaptainId(Long shipId, Long captainId, LocalDate startDate) {
        this.shipId = shipId;
        this.captainId = captainId;
        this.startDate = startDate;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public Long getCaptainId() {
        return captainId;
    }

    public void setCaptainId(Long captainId) {
        this.captainId = captainId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ShipCaptainId))
            return false;
        ShipCaptainId that = (ShipCaptainId) o;
        return Objects.equals(shipId, that.shipId)
                && Objects.equals(captainId, that.captainId)
                && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId, captainId, startDate);
    }

}
