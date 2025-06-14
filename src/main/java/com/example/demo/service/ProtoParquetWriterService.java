package com.example.demo.service;

import com.google.protobuf.Message;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.proto.ProtoParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.util.List;

public class ProtoParquetWriterService {

    /**
     * Write list of protobuf messages to a parquet file.
     * This is generic and supports any protobuf message type.
     */
    public static void writeToParquet(List<? extends Message> messages, Class<? extends Message> protoClass, String outputFilePath) {
        if (messages == null || messages.isEmpty()) {
            throw new IllegalArgumentException("Message list is empty");
        }

        try {
            Path path = new Path(outputFilePath);
            Configuration conf = new Configuration();

            try (ParquetWriter<Message> writer = ProtoParquetWriter
                    .<Message>builder(path)
                    .withMessage(protoClass)
                    .withCompressionCodec(CompressionCodecName.SNAPPY)
                    .withConf(conf)
                    .build()) {

                for (Message msg : messages) {
                    writer.write(msg);
                }

                System.out.println("Parquet file written to: " + outputFilePath);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to write proto to Parquet", e);
        }
    }
}

