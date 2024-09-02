package br.com.matheus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.matheus.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		logger.info("Finding all persons!");
		
		List <Person> persons = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;		
	}

	public Person findById(String id) {
		logger.info("Finding one person!");
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirsName("Matheus");
		person.setLastName("Freitas");
		person.setAddress("Recife - Pernambuco - Brasil");
		person.setGender("Male");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one person!");
		
	}
	
	private Person mockPerson(int i) {
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirsName("Matheus " + i);
		person.setLastName("Freitas " + i);
		person.setAddress("Recife - Pernambuco - Brasil " + i );
		person.setGender("Male");
		return person;
	}
}
