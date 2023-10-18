package br.com.serratec.ecommerce.model.exceptions;

public class ResourceBadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceBadRequestException(String message) {
        super(message);
    }
}
