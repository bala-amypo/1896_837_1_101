package com.example.demo.service.impl;

import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;

    public WarehouseServiceImpl(WarehouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Warehouse create(Warehouse warehouse) {
        return repository.save(warehouse);
    }

    @Override
    public Warehouse getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Warehouse> getAll() {
        return repository.findAll();
    }
}
