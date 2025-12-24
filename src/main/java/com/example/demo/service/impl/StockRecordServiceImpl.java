package com.example.demo.service.impl;

import com.example.demo.model.StockRecord;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository repo;

    public StockRecordServiceImpl(StockRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public StockRecord create(StockRecord record) {
        return repo.save(record);
    }

    @Override
    public List<StockRecord> getAll() {
        return repo.findAll();
    }

    @Override
    public List<StockRecord> getByProduct(Long productId) {
        return repo.findByProductId(productId);
    }
}
