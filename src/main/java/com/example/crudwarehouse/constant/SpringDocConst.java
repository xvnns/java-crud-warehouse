package com.example.crudwarehouse.constant;

/**
 * Contains constants for SpringDoc documentation
 */
public class SpringDocConst { //fixme
    public static final String INFO_TITLE = "Warehouse";
    public static final String INFO_DESCRIPTION = "CRUD application for warehouse";
    public static final String INFO_VERSION = "1.0";
    public static final String INFO_CONTACT_NAME = "Ekaterina Kostyreva";
    public static final String INFO_CONTACT_EMAIL = "katya.krivokoneva@gmail.com";
    public static final String NAME_SCHEMA_DESCRIPTION = "Product name";
    public static final String VENDOR_CODE_SCHEMA_DESCRIPTION = "Vendor code";
    public static final String DESCRIPTION_SCHEMA_DESCRIPTION = "Product description";
    public static final String CATEGORY_SCHEMA_DESCRIPTION = "Product category";
    public static final String PRICE_SCHEMA_DESCRIPTION = "Product price";
    public static final String QUANTITY_SCHEMA_DESCRIPTION = "Quantity";
    public static final String ID_SCHEMA_DESCRIPTION = "Product ID";
    public static final String TAG_NAME = "product";
    public static final String TAG_DESCRIPTION = "Allows interaction with products";
    public static final String CREATE_OPERATION_SUMMARY = "Add new product";
    public static final String CREATE_OPERATION_DESCRIPTION = "Add a new product to the warehouse";
    public static final String GET_ALL_OPERATION_SUMMARY = "Find all products";
    public static final String GET_ALL_OPERATION_DESCRIPTION = "Get a list of products from the warehouse. You can " +
            "pass the name and category of the product to the request parameters";
    public static final String GET_ALL_NAME_PARAMETER_DESCRIPTION = "Product name to consider when filtering";
    public static final String GET_ALL_CATEGORY_PARAMETER_DESCRIPTION = "Product category to consider when filtering";
    public static final String GET_BY_ID_OPERATION_SUMMARY = "Find product by Id";
    public static final String GET_BY_ID_OPERATION_DESCRIPTION = "Returns a single product";
    public static final String GET_BY_ID_ID_PARAMETER_DESCRIPTION = "Id of product to return";
    public static final String UPDATE_OPERATION_SUMMARY = "Update an existing product";
    public static final String UPDATE_OPERATION_DESCRIPTION = "Update an existing product by Id";
    public static final String UPDATE_ID_PARAMETER_DESCRIPTION = "Id of product to update";
    public static final String DELETE_BY_ID_OPERATION_SUMMARY = "Delete a product";
    public static final String DELETE_BY_ID_OPERATION_DESCRIPTION = "Delete a product by Id";
    public static final String DELETE_BY_ID_ID_PARAMETER_DESCRIPTION = "Id of product to delete";
}
