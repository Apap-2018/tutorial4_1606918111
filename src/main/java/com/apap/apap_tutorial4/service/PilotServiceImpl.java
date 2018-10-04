package com.apap.apap_tutorial4.service;

import com.apap.apap_tutorial4.model.PilotModel;
import com.apap.apap_tutorial4.repository.PilotDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotDb pilotDb;

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

    @Override
    public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}

	@Override
	public boolean removePilot(String licenseNumber) {
		pilotDb.delete(this.getPilotDetailByLicenseNumber(licenseNumber));
		return this.getPilotDetailByLicenseNumber(licenseNumber) == null;
	}

	public void updatePilot(PilotModel pilot){
		PilotModel findPilot = pilotDb.findByLicenseNumber(pilot.getLicenseNumber());
		findPilot.setName(pilot.getName());
		findPilot.setFlyHour(pilot.getFlyHour());
		
	}
}