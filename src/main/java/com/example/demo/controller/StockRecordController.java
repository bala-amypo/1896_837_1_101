package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import java.util.Map;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService stockRecordService;

    public StockRecordController(StockRecordService stockRecordService) {
        this.stockRecordService = stockRecordService;
    }

    @PostMapping("/{productId}/{warehouseId}")
    public ResponseEntity<StockRecord> create(@PathVariable Long productId,
                                              @PathVariable Long warehouseId,
                                              @RequestBody StockRecord sr) {
        return ResponseEntity.ok(stockRecordService.createStockRecord(productId, warehouseId, sr));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(stockRecordService.getStockRecord(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockRecord>> byProduct(@PathVariable Long id) {
        return ResponseEntity.ok(stockRecordService.getRecordsBy_product(id));
    }
}
