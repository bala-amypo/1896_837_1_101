package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository logRepo;
    private final StockRecordRepository stockRepo;

    public ConsumptionLogServiceImpl(ConsumptionLogRepository logRepo,
                                     StockRecordRepository stockRepo) {
        this.logRepo = logRepo;
        this.stockRepo = stockRepo;
    }

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord stockRecord = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedQuantity() == null || log.getConsumedQuantity() <= 0) {
            throw new IllegalArgumentException("consumedQuantity must be > 0");
        }
        if (log.getConsumedDate() == null) {
            throw new IllegalArgumentException("consumedDate must not be null");
        }
        if (log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future");
        }
         log.setStockRecord(stockRecord);
        return logRepo.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return logRepo.findByStockRecordId(stockRecordId);
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return logRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}
 