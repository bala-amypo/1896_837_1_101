package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController("warehouseController")
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<Warehouse> create(@RequestBody Warehouse w) {
        return ResponseEntity.ok(warehouseService.createWarehouse(w));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouse(id));
    }
}
