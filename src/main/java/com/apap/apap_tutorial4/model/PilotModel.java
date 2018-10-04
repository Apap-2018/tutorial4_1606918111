package com.apap.apap_tutorial4.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pilot")
public class PilotModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotNull
    @Column(name = "fly_hour", nullable = false)
    private int flyHour;

    @OneToMany(mappedBy = "pilot", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<FlightModel> pilotFlight;

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the licenseNumber
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * @param licenseNumber the licenseNumber to set
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int return the flyHour
     */
    public int getFlyHour() {
        return flyHour;
    }

    /**
     * @param flyHour the flyHour to set
     */
    public void setFlyHour(int flyHour) {
        this.flyHour = flyHour;
    }

    /**
     * @return List<FlightModel> return the pilotFlight
     */
    public List<FlightModel> getPilotFlight() {
        return pilotFlight;
    }

    /**
     * @param pilotFlight the pilotFlight to set
     */
    public void setPilotFlight(List<FlightModel> pilotFlight) {
        this.pilotFlight = pilotFlight;
    }

    public Boolean addPilotFlight(FlightModel flight) {
        return this.pilotFlight.add(flight);
    }

}