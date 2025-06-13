package com.example.demo.config;


public class FieldMapping {
    private String source;       // csv | hardcoded | function
    private String csvColumn;    // for source=csv
    private String value;        // for source=hardcoded or function name
    private String defaultValue; // optional
    private String type;         // optional

    // getters & setters
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getCsvColumn() { return csvColumn; }
    public void setCsvColumn(String csvColumn) { this.csvColumn = csvColumn; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getDefaultValue() { return defaultValue; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
