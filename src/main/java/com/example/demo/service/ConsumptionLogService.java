package com.example.demo.service;

import com.example.demo.model.ConsumptionLog;
import java.util.List;

public interface ConsumptionLogService {
    ConsumptionLog create(ConsumptionLog log);
    List<ConsumptionLog> getByStockRecord(Long stockRecordId);
}
