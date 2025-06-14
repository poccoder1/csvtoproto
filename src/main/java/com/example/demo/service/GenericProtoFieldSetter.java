package com.example.demo.service;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;

import java.util.*;

public class GenericProtoFieldSetter {

    public static void setField(DynamicMessage.Builder rootBuilder, String fieldPath, String value, Object unused) {
        List<String> parts = Arrays.asList(fieldPath.split("\\."));
        Deque<DynamicMessage.Builder> builderStack = new ArrayDeque<>();
        Deque<Descriptors.FieldDescriptor> fieldStack = new ArrayDeque<>();

        DynamicMessage.Builder currentBuilder = rootBuilder;
        Descriptors.Descriptor currentDescriptor = currentBuilder.getDescriptorForType();

        // Traverse the path and build nested builders
        for (int i = 0; i < parts.size() - 1; i++) {
            String part = parts.get(i);
            Descriptors.FieldDescriptor nestedField = currentDescriptor.findFieldByName(part);
            if (nestedField == null || nestedField.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                throw new IllegalArgumentException("Invalid nested field: " + fieldPath);
            }

            builderStack.push(currentBuilder);
            fieldStack.push(nestedField);

            DynamicMessage.Builder nestedBuilder;

            if (currentBuilder.hasField(nestedField)) {
                nestedBuilder = ((DynamicMessage) currentBuilder.getField(nestedField)).toBuilder();
            } else {
                nestedBuilder = DynamicMessage.newBuilder(nestedField.getMessageType());
            }

            currentBuilder = nestedBuilder;
            currentDescriptor = nestedBuilder.getDescriptorForType();
        }

        // Set the leaf field
        String finalFieldName = parts.get(parts.size() - 1);
        Descriptors.FieldDescriptor finalField = currentDescriptor.findFieldByName(finalFieldName);

        if (finalField == null) {
            throw new IllegalArgumentException("Field not found: " + finalFieldName);
        }

        Object parsedValue = convertValue(finalField, value);
        currentBuilder.setField(finalField, parsedValue);

        // Propagate changes up the stack to attach nested builders
        while (!builderStack.isEmpty()) {
            DynamicMessage.Builder parentBuilder = builderStack.pop();
            Descriptors.FieldDescriptor fieldInParent = fieldStack.pop();
            parentBuilder.setField(fieldInParent, currentBuilder.build());
            currentBuilder = parentBuilder;
        }

        // Now the original rootBuilder has the updated structure
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
