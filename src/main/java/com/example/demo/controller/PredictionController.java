package com.example.demo.controller;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/predict")
public class PredictionController {

    private final PredictionService service;

    public PredictionController(PredictionService service) {
        this.service = service;
    }

    @PostMapping("/rules")
    public ResponseEntity<PredictionRule> create(@RequestBody PredictionRule r) {
        return ResponseEntity.ok(service.createRule(r));
    }

    @GetMapping("/rules")
    public ResponseEntity<List<PredictionRule>> all() {
        return ResponseEntity.ok(service.getAllRules());
    }

    @GetMapping("/restock-date/{id}")
    public ResponseEntity<LocalDate> predict(@PathVariable Long id) {
        return ResponseEntity.ok(service.predictRestockDate(id));
    }
}
