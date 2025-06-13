package com.example.demo.util;

import org.apache.commons.csv.CSVRecord;

public class UtilityFunctions {
    public static String execute(String functionName, CSVRecord record) {
        // Example implementation
        if ("toUpperCaseExchange".equals(functionName)) {
            return record.get("exchange").toUpperCase();
        }
        return "";
    }
}