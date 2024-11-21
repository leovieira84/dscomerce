package br.com.leovieira.dscommerce.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.leovieira.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT obj FROM Product obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name , '%'))")
	Page<Product> searchByName(String name, Pageable pageable);
	
	@Query("SELECT obj FROM Product obj JOIN FETCH obj.categories WHERE obj in :products")
	List<Product> findProductsCategories(List<Product> products);
}
