package com.example.obejercicio456.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.obejercicio456.entities.Laptop;
import com.example.obejercicio456.repository.LaptopRepository;

@RestController
public class LaptopController {
	
	private final Logger log = LoggerFactory.getLogger(LaptopController.class);
	private LaptopRepository laptopRepository;

	public LaptopController(LaptopRepository laptop) {
		this.laptopRepository = laptop;
	}
	
	@GetMapping("/api/laptops")
	public List<Laptop> findAll(){
		return this.laptopRepository.findAll();
	}
	
	@GetMapping("/api/laptop/{id}")
	public ResponseEntity<Laptop> findById(@PathVariable Long id) {
		
		Optional<Laptop> laptopOpt = this.laptopRepository.findById(id);
		ResponseEntity<Laptop> ok = null;
		
		if(laptopOpt.isPresent()) {
			ok = ResponseEntity.ok(laptopOpt.get());
		}else {
			ok = ResponseEntity.notFound().build();
		}
		
		
		return ok;
	}
	
	@PostMapping("/api/books")
	public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
		ResponseEntity<Laptop> ok = ResponseEntity.accepted().build();

		if (laptop.getId() != null) {
			ok = ResponseEntity.badRequest().build();
			this.log.warn("Trying to create a book with id");
			System.out.println("Trying to create a book with id");
		} else {
			this.laptopRepository.save(laptop);
			System.out.println("CREADO " + laptop.toString());
		}

		System.out.println(headers.get("User-Agent"));
		return ok;
	}
	
	@PutMapping("/api/books")
	public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
		ResponseEntity<Laptop> ok = null;
		if (laptop.getId() == null) {
			log.warn("Trying to update a non existent book");
			ok = ResponseEntity.badRequest().build();
		} else if (!this.laptopRepository.existsById(laptop.getId())) {
			log.warn("Trying to update a non existent book");
			ok = ResponseEntity.notFound().build();
		} else {
			this.laptopRepository.save(laptop);
			ok = ResponseEntity.ok(this.laptopRepository.findById(laptop.getId()).get());

		}
		return ok;
	}
	
	@DeleteMapping("/api/books/{id}")
	public ResponseEntity<Laptop> delete(@PathVariable Long id){
		ResponseEntity<Laptop> ok = null;
		if(!this.laptopRepository.existsById(id)) {
			log.warn("This book doesnt Exist");
			ok = ResponseEntity.notFound().build();
		}else {
			this.laptopRepository.deleteById(id);
			
			ok = ResponseEntity.noContent().build();
		}
		
		return ok;
	}
	
	@DeleteMapping("/api/books")
	public ResponseEntity<Laptop> deleteAll(){
		log.info("DELETING ALL");
		this.laptopRepository.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
}
