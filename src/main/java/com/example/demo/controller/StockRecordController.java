package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockRecordController {

    private final StockRecordService service;

    @PostMapping("/{productId}/{warehouseId}")
    public StockRecord create(@PathVariable Long productId,
                              @PathVariable Long warehouseId,
                              @RequestBody StockRecord r) {
        return service.createStockRecord(productId, warehouseId, r);
    }

    @GetMapping("/{id}")
    public StockRecord get(@PathVariable Long id) {
        return service.getStockRecord(id);
    }

    @GetMapping("/product/{productId}")
    public List<StockRecord> byProduct(@PathVariable Long productId) {
        return service.getRecordsBy_product(productId);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public List<StockRecord> byWarehouse(@PathVariable Long warehouseId) {
        return service.getRecordsByWarehouse(warehouseId);
    }
}
