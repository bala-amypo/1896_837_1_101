package com.example.demo.service.impl;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository repo;

    public ConsumptionLogServiceImpl(ConsumptionLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public ConsumptionLog create(ConsumptionLog log) {
        return repo.save(log);
    }

    @Override
    public List<ConsumptionLog> getByStockRecord(Long stockRecordId) {
        return repo.findByStockRecordId(stockRecordId);
    }
}
