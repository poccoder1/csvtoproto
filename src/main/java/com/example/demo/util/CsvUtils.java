package com.example.demo.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;

public class CsvUtils {

    public static CSVParser readCsv(Reader reader) throws IOException {
        return CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withTrim()
                .parse(reader);
    }
}