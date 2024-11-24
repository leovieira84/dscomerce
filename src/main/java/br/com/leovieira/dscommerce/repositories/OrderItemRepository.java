package br.com.leovieira.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leovieira.dscommerce.entities.Order;
import br.com.leovieira.dscommerce.entities.OrderItem;
import br.com.leovieira.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{
	
}
