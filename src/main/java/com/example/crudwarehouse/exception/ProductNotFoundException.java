package com.example.crudwarehouse.exception;

import com.example.crudwarehouse.constant.ErrorMessages;

import java.util.UUID;

/**
 * Exception that is thrown when the product was not found
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(UUID uuid) {
        super(String.format(ErrorMessages.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE, uuid));
    }
}
