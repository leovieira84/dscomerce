package br.com.leovieira.dscommerce.dto;

import br.com.leovieira.dscommerce.entities.Product;

public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	public ProductDTO() {}
	
	public ProductDTO(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.imgUrl = p.getImgUrl();
	}
	
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
}
