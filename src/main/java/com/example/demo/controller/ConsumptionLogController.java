package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
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
    public ConsumptionLog log(@PathVariable Long stockRecordId,
                              @RequestBody ConsumptionLog log) {
        return service.logConsumption(stockRecordId, log);
    }

    @GetMapping("/record/{stockRecordId}")
    public List<ConsumptionLog> getByRecord(@PathVariable Long stockRecordId) {
        return service.getLogsByStockRecord(stockRecordId);
    }

    @GetMapping("/{id}")
    public ConsumptionLog get(@PathVariable Long id) {
        return service.getLog(id);
    }
}
