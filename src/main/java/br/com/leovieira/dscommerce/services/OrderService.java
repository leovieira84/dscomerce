package br.com.leovieira.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leovieira.dscommerce.dto.OrderDTO;
import br.com.leovieira.dscommerce.dto.OrderItemDTO;
import br.com.leovieira.dscommerce.entities.Order;
import br.com.leovieira.dscommerce.entities.OrderItem;
import br.com.leovieira.dscommerce.entities.OrderStatus;
import br.com.leovieira.dscommerce.entities.Product;
import br.com.leovieira.dscommerce.entities.User;
import br.com.leovieira.dscommerce.repositories.OrderItemRepository;
import br.com.leovieira.dscommerce.repositories.OrderRepository;
import br.com.leovieira.dscommerce.repositories.ProductRepository;
import br.com.leovieira.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository repository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	UserService userService;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order p = repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado")
		);
		return new OrderDTO(p);
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();
		
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.authenticated();
		order.setClient(user);
		
		for(OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(item);
		}
		
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}
}
