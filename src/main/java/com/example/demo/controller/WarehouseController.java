package com.example.demo.controller;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @PostMapping
    public Warehouse create(@RequestBody Warehouse warehouse) {
        return service.createWarehouse(warehouse);
    }

    @GetMapping
    public List<Warehouse> getAll() {
        return service.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable Long id) {
        return service.getWarehouse(id);
    }
}
