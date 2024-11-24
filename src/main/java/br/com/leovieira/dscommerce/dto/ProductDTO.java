package br.com.leovieira.dscommerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.leovieira.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	private Long id;
	@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String name;
	@Size(min = 10)
	@NotBlank(message = "Campo requerido")
	private String description;
	@NotNull(message = "Campo requerido")
	@Positive(message = "O preço deve ser prositivo")
	private Double price;
	private String imgUrl;
	
	@NotEmpty(message = "Deve-se informar ao menos uma categoria")
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProductDTO() {}
	
	public ProductDTO(Product p) {
		this.id = p.getId();
		this.name = p.getName();
		this.description = p.getDescription();
		this.price = p.getPrice();
		this.imgUrl = p.getImgUrl();
		
		this.categories = p.getCategories().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	public List<CategoryDTO> getCategories() {
		return categories;
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
