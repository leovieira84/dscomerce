package br.com.leovieira.dscommerce.dto;

import br.com.leovieira.dscommerce.entities.OrderItem;

public class OrderItemDTO {
	private Long produtId;
	private String name;
	private Double price;
	private Integer quantity;
	
	public OrderItemDTO(Long produtId, String name, Double price, Integer quantity) {
		this.produtId = produtId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public OrderItemDTO(OrderItem entity) {
		this.produtId = entity.getProduct().getId();
		this.name = entity.getProduct().getName();
		this.price = entity.getProduct().getPrice();
		this.quantity = entity.getQuantity();
	}

	public Long getProdutId() {
		return produtId;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public Double getSubtotal() {
		return price * quantity;
	}
	
}
