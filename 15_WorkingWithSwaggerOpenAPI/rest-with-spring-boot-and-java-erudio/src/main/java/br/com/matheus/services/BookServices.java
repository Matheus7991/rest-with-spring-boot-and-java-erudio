package br.com.matheus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import br.com.matheus.controllers.PersonController;
import br.com.matheus.data.vo.v1.PersonVO;
import br.com.matheus.exceptions.RequiredObjectIsNullException;
import br.com.matheus.exceptions.ResourceNotFoundException;
import br.com.matheus.exceptions.handler.CustomizedResponseEntityExceptionHandler;
import br.com.matheus.mapper.DozerMapper;
import br.com.matheus.model.Book;
import br.com.matheus.model.Person;
import br.com.matheus.repositories.BookRepository;
import br.com.matheus.repositories.PersonRepository;

@Service
public class BookServices {
	
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public List<Book> findAll() {
		logger.info("Finding all books!");
		
		List<Book> books = repository.findAll();
		
		
		/*books.stream().forEach(p -> {
			try {
				p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(p.getId())).withSelfRel());
			} catch (Exception e) {				
				e.printStackTrace();
			}
		});*/
		
		return books;
	}

	public Optional<Book> findById(Long id) {
		logger.info("Finding one person!");
		
		Optional<Book> entity = repository.findById(id);
		
		//var vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		/*try {
			vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {	
			e.printStackTrace();
		}*/
		
		return entity;
	}
	
	public Book create(Book book){
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one Book!");
		
		repository.save(book);
		
		//var entity = DozerMapper.parseObject(book, Person.class);
		
		//var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		/*try {
			vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		return book;
	}
	
	public PersonVO update(PersonVO person) {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		entity.setFirsName(person.getFirsName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		try {
			vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		repository.delete(entity);
		
	}
}
