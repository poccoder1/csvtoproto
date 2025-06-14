package com.example.demo.enums;

public enum SourceType {
    CSV("csv"),
    HARDCODED("hardcoded"),
    FUNCTION("function");

    private final String value;

    SourceType(String value) {
        this.value = value;
    }

    public static SourceType fromString(String input) {
        for (SourceType type : values()) {
            if (type.value.equalsIgnoreCase(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown source type: " + input);
    }
}
