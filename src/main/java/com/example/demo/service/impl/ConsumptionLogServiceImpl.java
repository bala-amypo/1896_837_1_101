package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository repo;
    private final StockRecordRepository stockRepo;

    public ConsumptionLogServiceImpl(ConsumptionLogRepository repo, StockRecordRepository stockRepo) {
        this.repo = repo;
        this.stockRepo = stockRepo;
    }

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord record = stockRepo.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedDate() != null && log.getConsumedDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("consumedDate cannot be future");

        log.setStockRecord(record);
        return repo.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return repo.findByStockRecordId(stockRecordId);
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}
