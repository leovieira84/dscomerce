package br.com.leovieira.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leovieira.dscommerce.dto.OrderDTO;
import br.com.leovieira.dscommerce.dto.ProductDTO;
import br.com.leovieira.dscommerce.services.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@GetMapping(value = "/{id}")
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
		OrderDTO dto = service.findById(id);
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	@PreAuthorize(value = "hasRole('ROLE_CLIENT')")
	public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) {
		dto = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
}
