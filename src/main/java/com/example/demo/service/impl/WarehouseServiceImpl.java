package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repo;

    public WarehouseServiceImpl(WarehouseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        if (warehouse.getLocation() == null || warehouse.getLocation().isBlank())
            throw new IllegalArgumentException("location cannot be empty");
        warehouse.setCreatedAt(LocalDateTime.now());
        return repo.save(warehouse);
    }

    @Override
    public Warehouse getWarehouse(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return repo.findAll();
    }
}
