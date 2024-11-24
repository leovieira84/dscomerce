package br.com.leovieira.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leovieira.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
