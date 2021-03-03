package com.product.exception;

public class ProductAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public ProductAlreadyExistException() {
        super();
    }

    public ProductAlreadyExistException( String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistException(String message) {
        super(message);
    }

    public ProductAlreadyExistException( Throwable cause) {
        super(cause);
    }

}
