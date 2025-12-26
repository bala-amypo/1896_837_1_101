package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    private StockRecordRepository stockRecordRepository;

    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;

    @Autowired
    private PredictionRuleRepository predictionRuleRepository;

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be greater than zero");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be <= maxDailyUsage");
        }
        if (predictionRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule name already exists");
        }
        rule.setCreatedAt(LocalDateTime.now());
        return predictionRuleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        List<PredictionRule> rules = predictionRuleRepository.findAll();
        PredictionRule rule = rules.isEmpty()
                ? PredictionRule.builder().averageDaysWindow(7).minDailyUsage(1).maxDailyUsage(100).build()
                : rules.get(0); // Use first rule as active/default

        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecordId);

        if (logs.isEmpty()) {
            return LocalDate.now().plusDays(30); // fallback
        }

        LocalDate cutoff = LocalDate.now().minusDays(rule.getAverageDaysWindow());
        long totalConsumed = logs.stream()
                .filter(log -> !log.getConsumedDate().isBefore(cutoff))
                .mapToInt(ConsumptionLog::getConsumedQuantity)
                .sum();

        double avgDaily = (double) totalConsumed / rule.getAverageDaysWindow();
        avgDaily = Math.max(avgDaily, rule.getMinDailyUsage());
        avgDaily = Math.min(avgDaily, rule.getMaxDailyUsage());

        if (avgDaily == 0) {
            return LocalDate.now().plusDays(365);
        }

        int daysUntilThreshold = (int) Math.ceil(
                (stockRecord.getCurrentQuantity() - stockRecord.getReorderThreshold()) / avgDaily);

        return LocalDate.now().plusDays(daysUntilThreshold);
    }
}