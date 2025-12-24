package com.example.demo.service;

import com.example.demo.model.Warehouse;
import java.util.List;

public interface WarehouseService {

    Warehouse create(Warehouse warehouse);

    Warehouse getById(Long id);

    List<Warehouse> getAll();
}
