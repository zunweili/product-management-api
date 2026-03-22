package com.example.demo.enums;

public enum ProductSortBy {

    PRICE("price"), CREATED_AT("createdAt");

    private final String fieldName;

    ProductSortBy(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
