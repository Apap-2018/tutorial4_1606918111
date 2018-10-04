package com.apap.apap_tutorial4.service;

import com.apap.apap_tutorial4.model.PilotModel;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    boolean removePilot(String licenseNumber);
    void addPilot(PilotModel pilot);
    void updatePilot(PilotModel pilot);

}