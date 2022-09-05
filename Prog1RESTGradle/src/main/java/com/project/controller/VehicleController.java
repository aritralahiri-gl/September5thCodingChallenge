package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dao.VehicleRepository;
import com.project.model.Vehicle;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	VehicleRepository vehicleRepo;

//	GET All Person REST
	@GetMapping("/")
	public List<Vehicle> getVehicle() {
		return vehicleRepo.findAll();

	}

//	GET Person By Id REST
	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> showVehicleById(@PathVariable Integer id) {
		Vehicle vehicle = vehicleRepo.findById(id).orElse(null);
		if (vehicle == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(vehicle);
	}

//	CREATE Person REST
	@PostMapping
	public Vehicle createPerson(@RequestBody Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}

	// delete person rest
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deletePerson(@PathVariable Integer id) {

		Vehicle vehicle = vehicleRepo.findById(id).orElse(null);
		if (vehicle == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		vehicleRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

//	Update Person REST
	@PutMapping("/{id}")
	public ResponseEntity<Vehicle> updatePerson(@PathVariable Integer id, @RequestBody Vehicle vehicle) {

		Vehicle vr = vehicleRepo.findById(id).orElse(null);

		if (vr == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		vr.setVehicleId(vehicle.getVehicleId());
		vr.setVehicleColor(vehicle.getVehicleColor());
		vr.setVehicleName(vehicle.getVehicleName());
		Vehicle updatedDataVehicle = vehicleRepo.save(vr);

		return ResponseEntity.ok(updatedDataVehicle);

	}

}
