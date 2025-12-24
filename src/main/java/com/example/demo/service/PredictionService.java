package com.example.demo.service;

import com.example.demo.model.PredictionRule;
import java.time.LocalDate;

public interface PredictionService {

    LocalDate predictRestockDate(Long stockRecordId);

    PredictionRule createRule(PredictionRule rule);

    java.util.List<PredictionRule> getAllRules();
}
