package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {
    private final ConsumptionLogRepository repo;
    private final StockRecordRepository stockRepo;

    @Override
    public ConsumptionLog logConsumption(Long stockRecordId, ConsumptionLog log) {
        StockRecord sr = stockRepo.findById(stockRecordId).orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
        if(log.getConsumedDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("consumedDate cannot be future");
        }
        log.setStockRecord(sr);
        // Logic to reduce stock could be here
        sr.setCurrentQuantity(Math.max(0, sr.getCurrentQuantity() - log.getConsumedQuantity()));
        stockRepo.save(sr);
        return repo.save(log);
    }

    @Override
    public List<ConsumptionLog> getLogsByStockRecord(Long stockRecordId) {
        return repo.findByStockRecordId(stockRecordId);
    }

    @Override
    public ConsumptionLog getLog(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}