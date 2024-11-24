package br.com.leovieira.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leovieira.dscommerce.dto.OrderDTO;
import br.com.leovieira.dscommerce.entities.Order;
import br.com.leovieira.dscommerce.repositories.OrderRepository;
import br.com.leovieira.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository repository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order p = repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado")
		);
		return new OrderDTO(p);
	}
}
