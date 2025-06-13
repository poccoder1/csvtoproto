package com.example.demo.controller;

import com.example.demo.service.CsvToProtoService;
import com.google.protobuf.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proto")
public class CsvToProtoController {

    @Autowired
    private CsvToProtoService service;

    @GetMapping("/{configName}")
    public List<String> convertToProto(@PathVariable String configName)
    {
        return service.convert(configName);
    }
}