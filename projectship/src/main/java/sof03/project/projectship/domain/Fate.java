package sof03.project.projectship.domain;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fateId;

    private String description;

    public Fate(){}

    public Fate(String description) {
        this.description = description;
    }

    public Long getFateId() {
        return fateId;
    }

    public void setFateId(Long fateId) {
        this.fateId = fateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
