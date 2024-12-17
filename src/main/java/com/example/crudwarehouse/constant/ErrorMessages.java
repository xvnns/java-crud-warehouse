package com.example.crudwarehouse.constant;

/**
 * Contains all warnings and error messages
 */
public class ErrorMessages {
    public static final String NAME_NOT_BLANK_MESSAGE = "Name must not be blank";
    public static final String NAME_NOT_NULL_MESSAGE = "Please enter name";
    public static final String NAME_SIZE_MAX_MESSAGE = "Name must not be more than 90 characters";
    public static final String VENDOR_CODE_NOT_BLANK_MESSAGE = "Vendor code must not be blank";
    public static final String VENDOR_CODE_NOT_NULL_MESSAGE = "Please enter vendor code";
    public static final String VENDOR_CODE_SIZE_MAX_MESSAGE = "Vendor code must not be more than 255 characters";
    public static final String DESCRIPTION_NOT_BLANK_MESSAGE = "Description must not be blank";
    public static final String DESCRIPTION_SIZE_MAX_MESSAGE = "Description must not be more than 2000 characters";
    public static final String CATEGORY_NOT_NULL_MESSAGE = "Please enter category";
    public static final String PRICE_NOT_NULL_MESSAGE = "Please enter price";
    public static final String PRICE_MIN_VALUE_MESSAGE = "Price cannot be less than 0.0";
    public static final String QUANTITY_NOT_NULL_MESSAGE = "Please enter quantity";
    public static final String QUANTITY_MIN_VALUE_MESSAGE = "Quantity cannot be less than 0";
    public static final String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "Product with id %s not found";
    public static final String PRODUCT_WITH_THIS_VENDOR_CODE_ALREADY_EXISTS_EXCEPTION_MESSAGE = "A product with vendor code %s already exists";
    public static final String SEARCH_OPERATION_NOT_FOUND_EXCEPTION_MESSAGE = "Unexpected value: %s";
}
