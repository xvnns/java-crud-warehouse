package com.example.crudwarehouse.search;

import com.example.crudwarehouse.exception.SearchOperationNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SearchOperation {
    EQUAL("="),
    GRATER_THAN_OR_EQUAL_TO(">="),
    LESS_THAN_OR_EQUAL_TO("<="),
    LIKE("~");

    private final String code;

    SearchOperation(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static SearchOperation fromCode(String code) throws SearchOperationNotFoundException {
        for (SearchOperation operationType : SearchOperation.values()) {
            if (operationType.name().equals(code) || operationType.code.equals(code)) {
                return operationType;
            }
        }
        throw new SearchOperationNotFoundException(code);
    }
}
