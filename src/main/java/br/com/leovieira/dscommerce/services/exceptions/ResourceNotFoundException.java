package br.com.leovieira.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		super("Recurso não encontrado");
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
