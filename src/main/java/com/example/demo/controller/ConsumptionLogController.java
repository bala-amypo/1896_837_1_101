package com.example.demo.controller;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    public ConsumptionLogController(ConsumptionLogService service) {
        this.service = service;
    }

    @PostMapping("/{stockId}")
    public ResponseEntity<ConsumptionLog> log(@PathVariable Long stockId, @RequestBody ConsumptionLog log) {
        return ResponseEntity.ok(service.logConsumption(stockId, log));
    }

    @GetMapping("/record/{id}")
    public ResponseEntity<List<ConsumptionLog>> logs(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLogsByStockRecord(id));
    }
}
