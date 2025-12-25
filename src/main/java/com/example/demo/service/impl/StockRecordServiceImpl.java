package com.example.demo.service.impl;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockRecordServiceImpl implements StockRecordService {
    public StockRecord createStockRecord(Long p, Long w, StockRecord r) { return r; }
    public StockRecord getStockRecord(Long id) { return new StockRecord(); }
    public List<StockRecord> getRecordsBy_product(Long id) { return List.of(); }
    public List<StockRecord> getRecordsByWarehouse(Long id) { return List.of(); }
}
