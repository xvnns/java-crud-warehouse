package com.example.crudwarehouse.exception;

import com.example.crudwarehouse.constant.ErrorMessages;

import java.util.UUID;

/**
 * Exception that is thrown when search operation {@link com.example.crudwarehouse.search.SearchOperation} was not found
 */
public class SearchOperationNotFoundException extends Exception {
    public SearchOperationNotFoundException(String str) {
        super(String.format(ErrorMessages.SEARCH_OPERATION_NOT_FOUND_EXCEPTION_MESSAGE, str));
    }
}
