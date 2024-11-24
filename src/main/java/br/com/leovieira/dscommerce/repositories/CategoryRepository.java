package br.com.leovieira.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leovieira.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
