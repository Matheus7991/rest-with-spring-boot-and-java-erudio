package br.com.matheus.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.exceptions.ResourceNotFoundException;
import br.com.matheus.model.Person;
import br.com.matheus.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("Finding all persons!");
		
		return repository.findAll();		
	}

	public Person findById(Long id) {
		logger.info("Finding one person!");
		
		Person person = new Person();
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		entity.setFirsName(person.getFirsName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		repository.delete(entity);
		
	}
}