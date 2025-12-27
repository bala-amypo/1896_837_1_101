package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StockRecordServiceImpl implements StockRecordService {
    private final StockRecordRepository stockRepo;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record) {
        Product p = productRepo.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Warehouse w = warehouseRepo.findById(warehouseId)
            .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        // Check for duplicates (Test 21 & 44)
        if (!stockRepo.findByProductIdAndWarehouseId(productId, warehouseId).isEmpty()) {
            throw new IllegalArgumentException("StockRecord already exists");
        }

        if (record.getCurrentQuantity() < 0 || record.getReorderThreshold() <= 0) {
            throw new IllegalArgumentException("Invalid quantities");
        }

        record.setProduct(p);
        record.setWarehouse(w);
        record.setLastUpdated(LocalDateTime.now());
        return stockRepo.save(record);
    }
    // ... other methods
}