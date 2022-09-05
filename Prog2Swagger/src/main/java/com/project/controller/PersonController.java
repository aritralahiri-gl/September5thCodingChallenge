package com.project.controller;

import java.util.List;

import org.hibernate.sql.Update;
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

import com.project.dao.PersonRepository;
import com.project.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonRepository personRepo;

//	GET All Person REST
	@GetMapping("/")
	public List<Person> getPerson() {
		return personRepo.findAll();

	}

//	GET Person By Id REST
	@GetMapping("/{id}")
	public ResponseEntity<Person> showPersonById(@PathVariable Integer id) {
		Person person = personRepo.findById(id).orElse(null);
		if (person == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(person);
	}

//	CREATE Person REST
	@PostMapping
	public Person createPerson(@RequestBody Person person) {
		return personRepo.save(person);
	}

	// delete person rest
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deletePerson(@PathVariable Integer id) {

		Person person = personRepo.findById(id).orElse(null);
		if (person == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		personRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

//	Update Person REST
	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {

		Person pr = personRepo.findById(id).orElse(null);

		if (pr == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		pr.setPersonId(person.getPersonId());
		pr.setPassion(person.getPassion());
		pr.setPersonName(person.getPersonName());
		Person updatedDataPerson = personRepo.save(pr);

		return ResponseEntity.ok(updatedDataPerson);

	}

}
