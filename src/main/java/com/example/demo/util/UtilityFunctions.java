package com.example.demo.util;

import com.example.demo.config.FieldMapping;
import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UtilityFunctions {

    public static String invokeDynamicFunction(FieldMapping.FunctionDetails function, CSVRecord record) {
        try {
            Class<?> clazz = Class.forName(function.getClassName());
            Method method;

            // Prepare parameter values from CSV or as literals
            List<Object> paramValues = new ArrayList<>();
            List<Class<?>> paramTypes = new ArrayList<>();

            for (String param : function.getParams()) {
                String value;
                if (record.isMapped(param)) {
                    value = record.get(param); // CSV column
                } else {
                    value = param; // treat as constant
                }
                paramValues.add(value);
                paramTypes.add(String.class); // assuming all functions take Strings
            }

            method = clazz.getDeclaredMethod(function.getMethod(), paramTypes.toArray(new Class[0]));
            Object result = method.invoke(null, paramValues.toArray());

            return result.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error invoking function: " + function.getMethod(), e);
        }
    }

    public static String execute(String functionName, CSVRecord record) {
        // Example implementation
        if ("toUpperCaseExchange".equals(functionName)) {
            return record.get("exchange").toUpperCase();
        }
        return "";
    }

    public static String generateTradeKey(String accountId, String qty) {
        return accountId + "_"  + qty;
    }
}