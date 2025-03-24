package com.max.api.DTO;

/**
 * @author Max
 * @description    
 * @date 2025/3/24 18:04
 */
public enum SearchMethods {
    SUB_COUNT_DESC("subCount-desc"),
    SUB_COUNT_ASC("subCount-asc"),
    NAME_ASC("name-asc"),
    NAME_DESC("name-desc"),
    SUB_TIME_DESC("subTime-desc"),
    SUB_TIME_ASC("subTime-asc");

    private final String value;

    SearchMethods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("SearchMethods{value='%s'}", value);
    }
}
