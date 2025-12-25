package com.example.demo.service.impl;

import com.example.demo.model.PredictionRule;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {
    public LocalDate predictRestockDate(Long id) { return LocalDate.now().plusDays(1); }
    public PredictionRule createRule(PredictionRule rule) { return rule; }
    public List<PredictionRule> getAllRules() { return List.of(); }
}
