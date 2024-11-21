package br.com.leovieira.dscommerce.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.leovieira.dscommerce.dto.ProductDTO;
import br.com.leovieira.dscommerce.entities.Product;
import br.com.leovieira.dscommerce.repositories.ProductRepository;
import br.com.leovieira.dscommerce.services.exceptions.DatabaseException;
import br.com.leovieira.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product p = repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrado")
		);
		return new ProductDTO(p);
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(String name, Pageable pageable) {
		Page<Product> page = repository.findAll(pageable);
		
		repository.findProductsCategories(page.stream().collect(Collectors.toList()));
		
		return page.map(x -> new ProductDTO(x));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException();
		}
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
		
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
	}
}
