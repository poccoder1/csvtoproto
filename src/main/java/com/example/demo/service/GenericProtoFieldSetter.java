package com.example.demo.service;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;

import java.util.Arrays;
import java.util.List;

public class GenericProtoFieldSetter {

    public static void setField(DynamicMessage.Builder builder, String fieldPath, String value, Object unused) {
        List<String> parts = Arrays.asList(fieldPath.split("\\."));
        DynamicMessage.Builder currentBuilder = builder;
        Descriptors.Descriptor currentDescriptor = builder.getDescriptorForType();

        for (int i = 0; i < parts.size() - 1; i++) {
            String part = parts.get(i);
            Descriptors.FieldDescriptor nestedField = currentDescriptor.findFieldByName(part);

            if (nestedField == null || nestedField.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new IllegalArgumentException("Invalid nested field: " + fieldPath);
            }

            DynamicMessage.Builder nestedBuilder = DynamicMessage.newBuilder(nestedField.getMessageType());
            currentBuilder.setField(nestedField, nestedBuilder.build()); // set empty to initialize
            currentBuilder = nestedBuilder;
            currentDescriptor = nestedBuilder.getDescriptorForType();
        }

        String finalFieldName = parts.get(parts.size() - 1);
        Descriptors.FieldDescriptor finalField = currentDescriptor.findFieldByName(finalFieldName);
        if (finalField == null) {
            throw new IllegalArgumentException("Field not found: " + fieldPath);
        }

        Object parsedValue = convertValue(finalField, value);
        currentBuilder.setField(finalField, parsedValue);
    }

    private static Object convertValue(Descriptors.FieldDescriptor field, String value) {
        switch (field.getJavaType()) {
            case STRING:
                return value;
            case INT:
                return Integer.parseInt(value);
            case LONG:
                return Long.parseLong(value);
            case FLOAT:
                return Float.parseFloat(value);
            case DOUBLE:
                return Double.parseDouble(value);
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            case ENUM:
                return field.getEnumType().findValueByName(value.toUpperCase());
            default:
                throw new UnsupportedOperationException("Unsupported type: " + field.getJavaType());
        }
    }
}