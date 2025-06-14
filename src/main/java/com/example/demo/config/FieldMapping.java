package com.example.demo.config;

import java.util.List;

public class FieldMapping {
    private String source; // csv, hardcoded, function
    private String csvColumn;
    private String defaultValue;
    private String value;
    private String type;

    private FunctionDetails function;

    public static class FunctionDetails {
        private String className;
        private String method;
        private List<String> params;

        // Getters and setters
        public String getClassName() { return className; }
        public void setClassName(String className) { this.className = className; }

        public String getMethod() { return method; }
        public void setMethod(String method) { this.method = method; }

        public List<String> getParams() { return params; }
        public void setParams(List<String> params) { this.params = params; }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCsvColumn() {
        return csvColumn;
    }

    public void setCsvColumn(String csvColumn) {
        this.csvColumn = csvColumn;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FunctionDetails getFunction() {
        return function;
    }

    public void setFunction(FunctionDetails function) {
        this.function = function;
    }
// Getters and setters for all fields...
}

