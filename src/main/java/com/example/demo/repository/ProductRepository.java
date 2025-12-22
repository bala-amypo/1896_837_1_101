package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Optional<Warehouse> findByWarehouseName(String warehouseName);
}

public interface StockRecordRepository extends JpaRepository<StockRecord, Long> {
    List<StockRecord> findByProductId(Long productId);
    List<StockRecord> findByWarehouseId(Long warehouseId);
}

public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {
    List<ConsumptionLog> findByStockRecordId(Long stockRecordId);
}

public interface PredictionRuleRepository extends JpaRepository<PredictionRule, Long> {
    Optional<PredictionRule> findByRuleName(String ruleName);
}

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
