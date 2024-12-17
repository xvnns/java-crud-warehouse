package com.example.crudwarehouse.search.criteria;

import com.example.crudwarehouse.search.SearchOperation;
import com.example.crudwarehouse.search.predicate.PredicateStrategy;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "field",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BigDecimalSearchCriteria.class, name = "price"),
        @JsonSubTypes.Type(value = StringSearchCriteria.class, name = "name"),
        @JsonSubTypes.Type(value = LocalDateTimeSearchCriteria.class, name = "create_ts"),
        @JsonSubTypes.Type(value = BigDecimalSearchCriteria.class, name = "quantity")
})
public abstract class SearchCriteria<T> {

    private static final char DELIMITER = '_';

    protected String field;
    @Getter
    protected T value;
    @Getter
    protected SearchOperation operation;

    public String getField() {
        return toCamelCase(field);
    }

    private String toCamelCase(String text) {
        boolean shouldConvertNextCharToLower = true;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar == DELIMITER) {
                shouldConvertNextCharToLower = false;
            } else if (shouldConvertNextCharToLower) {
                builder.append(Character.toLowerCase(currentChar));
            } else {
                builder.append(Character.toUpperCase(currentChar));
                shouldConvertNextCharToLower = true;
            }
        }
        return builder.toString();
    }

    public abstract PredicateStrategy<T> getStrategy();
}
