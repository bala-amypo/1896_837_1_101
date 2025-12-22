package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final StockRecordRepository stockRepo;
    private final ConsumptionLogRepository logRepo;
    private final PredictionRuleRepository ruleRepo;

    public PredictionServiceImpl(StockRecordRepository stockRepo,
                                 ConsumptionLogRepository logRepo,
                                 PredictionRuleRepository ruleRepo) {
        this.stockRepo = stockRepo;
        this.logRepo = logRepo;
        this.ruleRepo = ruleRepo;
    }
    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getRuleName() == null || rule.getRuleName().isBlank()) {
            throw new IllegalArgumentException("ruleName must not be blank");
        }
        if (rule.getAverageDaysWindow() == null || rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be > 0");
        }
        if (rule.getMinDailyUsage() == null || rule.getMaxDailyUsage() == null) {
            throw new IllegalArgumentException("minDailyUsage and maxDailyUsage must not be null");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be <= maxDailyUsage");
        }
        ruleRepo.findByRuleName(rule.getRuleName()).ifPresent(r -> {
            throw new IllegalArgumentException("ruleName must be unique");
        });
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepo.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepo.findAll();
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getRuleName() == null || rule.getRuleName().isBlank()) {
            throw new IllegalArgumentException("ruleName must not be blank");
        }
        if (rule.getAverageDaysWindow() == null || rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be > 0");
        }
        if (rule.getMinDailyUsage() == null || rule.getMaxDailyUsage() == null) {
            throw new IllegalArgumentException("minDailyUsage and maxDailyUsage must not be null");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be <= maxDailyUsage");
        }
        ruleRepo.findByRuleName(rule.getRuleName()).ifPresent(r -> {
            throw new IllegalArgumentException("ruleName must be unique");
        });
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepo.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepo.findAll();
    }

