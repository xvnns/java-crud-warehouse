package com.example.crudwarehouse.exception;

import com.example.crudwarehouse.constant.ErrorMessages;

/**
 * Exception that is thrown when a product with the corresponding vendor code already exists in the database
 */
public class ProductWithThisVendorCodeAlreadyExistsException extends Exception {
    public ProductWithThisVendorCodeAlreadyExistsException(String vendorCode) {
        super(String.format(ErrorMessages.PRODUCT_WITH_THIS_VENDOR_CODE_ALREADY_EXISTS_EXCEPTION_MESSAGE, vendorCode));
    }
}
