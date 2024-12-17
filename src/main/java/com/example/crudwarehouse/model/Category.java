package com.example.crudwarehouse.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nullable;

public enum Category {
    PHONE("phone"),
    TABLET("tablet"),
    LAPTOP("laptop"),
    HEADPHONES("headphones");

    private final String id;

    Category(String id) {
        this.id = id;
    }

    @JsonValue
    public String getId() {
        return id;
    }

    @Nullable
    public static Category fromId(String id) {
        for (Category at : Category.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
