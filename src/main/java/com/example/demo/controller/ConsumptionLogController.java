package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionLogController {

    @Autowired
    private ConsumptionLogService consumptionLogService;

    @PostMapping("/{stockRecordId}")
    public ConsumptionLog logConsumption(@PathVariable Long stockRecordId,
                                         @RequestBody ConsumptionLog log) {
        return consumptionLogService.logConsumption(stockRecordId, log);
    }

    @GetMapping("/record/{stockRecordId}")
    public List<ConsumptionLog> getLogsByStockRecord(@PathVariable Long stockRecordId) {
        return consumptionLogService.getLogsByStockRecord(stockRecordId);
    }

    @GetMapping("/{id}")
    public ConsumptionLog getLog(@PathVariable Long id) {
        return consumptionLogService.getLog(id);
    }
}