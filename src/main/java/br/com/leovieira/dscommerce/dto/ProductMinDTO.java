package br.com.leovieira.dscommerce.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.leovieira.dscommerce.entities.Product;

public class ProductMinDTO {
	private Long id;
	private String name;
	private Double price;
	private String imgUrl;
	
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProductMinDTO() {}
	
	public ProductMinDTO(Long id, String name, Double price, String imgUrl, List<CategoryDTO> categories) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgUrl = imgUrl;
		this.categories = categories;
	}

	public ProductMinDTO(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.price = p.getPrice();
		this.imgUrl = p.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}
}
