package com.example.demo.config;

import java.util.Map;

public class MappingConfig {
    private String protoClass;
    private Map<String, FieldMapping> fields;

    // getters & setters
    public String getProtoClass() { return protoClass; }
    public void setProtoClass(String protoClass) { this.protoClass = protoClass; }

    public Map<String, FieldMapping> getFields() { return fields; }
    public void setFields(Map<String, FieldMapping> fields) { this.fields = fields; }
}
