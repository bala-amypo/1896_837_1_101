package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumption")
@RequiredArgsConstructor
public class ConsumptionLogController {

    private final ConsumptionLogService consumptionLogService;

    @PostMapping("/{stockRecordId}")
    public ConsumptionLog log(@PathVariable Long stockRecordId,
                              @RequestBody ConsumptionLog log) {
        return consumptionLogService.logConsumption(stockRecordId, log);
    }

    @GetMapping("/record/{stockRecordId}")
    public List<ConsumptionLog> byRecord(@PathVariable Long stockRecordId) {
        return consumptionLogService.getLogsByStockRecord(stockRecordId);
    }

    @GetMapping("/{id}")
    public ConsumptionLog get(@PathVariable Long id) {
        return consumptionLogService.getLog(id);
    }
}
