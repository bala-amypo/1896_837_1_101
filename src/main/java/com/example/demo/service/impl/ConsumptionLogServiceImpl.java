package com.example.demo.service.impl;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    public ConsumptionLog logConsumption(Long id, ConsumptionLog log) { return log; }
    public List<ConsumptionLog> getLogsByStockRecord(Long id) { return List.of(); }
    public ConsumptionLog getLog(Long id) { return new ConsumptionLog(); }
}
