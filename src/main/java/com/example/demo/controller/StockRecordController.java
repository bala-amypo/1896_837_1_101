package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService service;

    public StockRecordController(StockRecordService service) {
        this.service = service;
    }

    @PostMapping("/{productId}/{warehouseId}")
    public ResponseEntity<StockRecord> create(@PathVariable Long productId,
                                              @PathVariable Long warehouseId,
                                              @RequestBody StockRecord record) {
        return ResponseEntity.ok(service.createStockRecord(productId, warehouseId, record));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<StockRecord>> byProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getRecordsBy_product(productId));
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<StockRecord>> byWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(service.getRecordsByWarehouse(warehouseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStockRecord(id));
    }
}
