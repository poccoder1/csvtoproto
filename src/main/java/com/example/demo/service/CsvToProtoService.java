package com.example.demo.service;
import com.example.demo.config.FieldMapping;
import com.example.demo.config.MappingConfig;
import com.example.demo.util.CsvUtils;
import com.example.demo.util.UtilityFunctions;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CsvToProtoService {

    public List<String> convert(String configName) {
        try (Reader reader = Files.newBufferedReader(Path.of("src/main/resources/data/" + configName + ".csv"))) {
            CSVParser parser = CsvUtils.readCsv(reader);

            InputStream ymlStream = getClass().getClassLoader().getResourceAsStream("config/" + configName + ".yml");
            LoaderOptions options = new LoaderOptions();
            Constructor constructor = new Constructor(MappingConfig.class, options);
            Yaml yaml = new Yaml(constructor);
            MappingConfig mappingConfig = yaml.load(ymlStream);

            Class<?> protoClass = Class.forName(mappingConfig.getProtoClass());
            Message defaultInstance = (Message) protoClass.getMethod("getDefaultInstance").invoke(null);
            Descriptors.Descriptor descriptor = defaultInstance.getDescriptorForType();

            List<String> jsonResults = new ArrayList<>();

            List<Message> protoMessages = new ArrayList<>();

            for (CSVRecord record : parser) {
                DynamicMessage.Builder builder = DynamicMessage.newBuilder(descriptor);

                for (Map.Entry<String, FieldMapping> entry : mappingConfig.getFields().entrySet()) {
                    String fieldPath = entry.getKey();
                    FieldMapping fieldMapping = entry.getValue();

                    String value = null;
                    switch (fieldMapping.getSource()) {
                        case "csv":
                            value = record.get(fieldMapping.getCsvColumn());
                            if ((value == null || value.isEmpty()) && fieldMapping.getDefaultValue() != null) {
                                value = fieldMapping.getDefaultValue();
                            }
                            break;
                        case "hardcoded":
                            value = fieldMapping.getValue();
                            break;
                        case "function":
                            value = UtilityFunctions.invokeDynamicFunction(fieldMapping.getFunction(), record);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown source type: " + fieldMapping.getSource());
                    }

                    GenericProtoFieldSetter.setField(builder, fieldPath, value, null);
                }

                Message msg = builder.build();
                protoMessages.add(msg);
                jsonResults.add(JsonFormat.printer().print(msg));
            }

            // Write to Parquet using proto message type
            ProtoParquetWriterService.writeToParquet(
                    protoMessages,
                    (Class<? extends Message>) protoClass,
                    "output/ccp_balances.parquet"
            );

            return jsonResults;

        } catch (Exception e) {
            throw new RuntimeException("Failed to convert CSV to proto", e);
        }
    }
}
