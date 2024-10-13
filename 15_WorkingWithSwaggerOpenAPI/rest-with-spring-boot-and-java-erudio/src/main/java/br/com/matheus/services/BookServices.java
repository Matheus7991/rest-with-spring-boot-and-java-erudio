package br.com.matheus.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import br.com.matheus.controllers.BookController;
import br.com.matheus.data.vo.v1.BookVO;
import br.com.matheus.exceptions.RequiredObjectIsNullException;
import br.com.matheus.exceptions.ResourceNotFoundException;
import br.com.matheus.mapper.DozerMapper;
import br.com.matheus.model.Book;
import br.com.matheus.repositories.BookRepository;

@Service
public class BookServices {
	
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	public List<BookVO> findAll() {
		logger.info("Finding all books!");
		
		var books =  DozerMapper.parseListObjects(repository.findAll(), BookVO.class);			
		
		books.stream().forEach(p -> {
			try {
				p.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {				
				e.printStackTrace();
			}
		});
		
		return books;
	}
	

	public BookVO findById(Long id) {
		logger.info("Finding one book!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		
		try {
			vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public BookVO create(BookVO book){
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book!");
		
		var entity = DozerMapper.parseObject(book, Book.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		try {
			vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	public BookVO update(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one book!");
		
		var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		try {
			vo.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one book!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		repository.delete(entity);
		
	}
}
