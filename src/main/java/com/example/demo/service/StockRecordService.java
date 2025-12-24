package com.example.demo.service;

import com.example.demo.model.StockRecord;
import java.util.List;

public interface StockRecordService {
    StockRecord create(StockRecord record);
    List<StockRecord> getAll();
    List<StockRecord> getByProduct(Long productId);
}
