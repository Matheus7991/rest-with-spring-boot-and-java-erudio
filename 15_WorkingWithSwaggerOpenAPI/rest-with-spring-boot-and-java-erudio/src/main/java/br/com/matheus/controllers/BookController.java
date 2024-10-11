package br.com.matheus.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.data.vo.v1.BookVO;
import br.com.matheus.data.vo.v1.PersonVO;
import br.com.matheus.model.Book;
import br.com.matheus.services.BookServices;
import br.com.matheus.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for managing Book")
public class BookController {
	
	@Autowired
	private BookServices service;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML} )
	@Operation(summary = "Finds all Books", description = "Finds all Books", tags = {"Book"}, 
				responses = { @ApiResponse(description = "Success", responseCode = "200", 
								content = {
											@Content(
													mediaType = "application/json",
													array = @ArraySchema(schema = @Schema(implementation = Book.class))
													)
											}),
						      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
						      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
						      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
						      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
				}
			)
	public List<Book> findAll() throws Exception {
		
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Book", description = "Finds a Book", tags = {"Book"}, 
	responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = Book.class))),
				  @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
		)
	public Optional<Book> findById(@PathVariable(value = "id") Long id) throws Exception {
		
		return (Optional<Book>) service.findById(id);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a new Person", description = "Adds a new Person by passing in a JSON, XML or YML representation of the person!", tags = {"Book"}, 
	responses = { @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),			
			      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
		)
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		
		return service.create(person);
	}
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a Person", description = "Updates a Person by passing in a JSON, XML or YML representation of the person!", tags = {"Book"}, 
	responses = { @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
			      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
		)
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Book", description = "Deletes a Book by passing in a JSON, XML or YML representation of the book!", tags = {"Book"}, 
	responses = { @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
		)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}


}
