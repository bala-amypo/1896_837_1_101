package com.example.demo.service.impl;

import com.example.demo.model.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    public Warehouse createWarehouse(Warehouse warehouse) { return warehouse; }
    public Warehouse getWarehouse(Long id) { return new Warehouse(); }
    public List<Warehouse> getAllWarehouses() { return List.of(); }
}
