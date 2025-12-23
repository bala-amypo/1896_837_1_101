package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    public ConsumptionLogController(ConsumptionLogService service) {
        this.service = service;
    }

    @PostMapping("/{stockRecordId}")
    public ResponseEntity<ConsumptionLog> create(@PathVariable Long stockRecordId,
                                                 @RequestBody ConsumptionLog log) {
        return ResponseEntity.ok(service.logConsumption(stockRecordId, log));
    }

    @GetMapping("/record/{stockRecordId}")
    public ResponseEntity<List<ConsumptionLog>> byStockRecord(@PathVariable Long stockRecordId) {
        return ResponseEntity.ok(service.getLogsByStockRecord(stockRecordId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionLog> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLog(id));
    }
}
