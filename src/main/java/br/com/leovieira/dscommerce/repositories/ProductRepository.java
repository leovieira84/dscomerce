package br.com.leovieira.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leovieira.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
