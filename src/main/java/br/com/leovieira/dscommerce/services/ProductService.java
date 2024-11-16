package br.com.leovieira.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leovieira.dscommerce.dto.ProductDTO;
import br.com.leovieira.dscommerce.entities.Product;
import br.com.leovieira.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product p = repository.findById(id).get();
		return new ProductDTO(p);
	}
}
