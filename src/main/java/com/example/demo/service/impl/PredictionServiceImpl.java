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

    private final PredictionRuleRepository ruleRepository;
    private final StockRecordRepository stockRecordRepository;
    private final ConsumptionLogRepository consumptionLogRepository;

    public PredictionServiceImpl(PredictionRuleRepository ruleRepository,
                                 StockRecordRepository stockRecordRepository,
                                 ConsumptionLogRepository consumptionLogRepository) {
        this.ruleRepository = ruleRepository;
        this.stockRecordRepository = stockRecordRepository;
        this.consumptionLogRepository = consumptionLogRepository;
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getRuleName() == null || rule.getRuleName().trim().isEmpty()) {
            throw new IllegalArgumentException("ruleName must not be empty");
        }
        if (rule.getAverageDaysWindow() == null || rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("averageDaysWindow must be greater than zero");
        }
        if (rule.getMinDailyUsage() == null || rule.getMaxDailyUsage() == null) {
            throw new IllegalArgumentException("minDailyUsage and maxDailyUsage must be provided");
        }
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("minDailyUsage must be less than or equal to maxDailyUsage");
        }
        ruleRepository.findByRuleName(rule.getRuleName()).ifPresent(r -> {
            throw new IllegalArgumentException("ruleName must be unique");
        });
        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        // Use the most recently created rule as the active rule (simple default).
        PredictionRule rule = ruleRepository.findAll().stream()
                .max(Comparator.comparing(PredictionRule::getCreatedAt))
                .orElseThrow(() -> new IllegalArgumentException("No prediction rules configured"));

        int window = rule.getAverageDaysWindow();
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(window - 1L);

        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecord.getId());
        long totalConsumedInWindow = logs.stream()
                .filter(l -> !l.getConsumedDate().isBefore(startDate) && !l.getConsumedDate().isAfter(today))
                .mapToLong(l -> l.getConsumedQuantity() != null ? l.getConsumedQuantity() : 0)
                .sum();

        double averageDailyUsage = window > 0 ? (double) totalConsumedInWindow / window : 0.0;

        // Clamp average to rule bounds
        averageDailyUsage = Math.max(averageDailyUsage, rule.getMinDailyUsage());
        averageDailyUsage = Math.min(averageDailyUsage, rule.getMaxDailyUsage());

        int currentQty = stockRecord.getCurrentQuantity();
        int threshold = stockRecord.getReorderThreshold();

        if (currentQty <= threshold) {
            return today; // already at or below threshold; restock immediately
        }
        if (averageDailyUsage <= 0) {
            // No consumption; predict far future (or today). We'll pick today to avoid infinite date.
            return today;
        }

        int qtyToConsume = currentQty - threshold;
        int daysRemaining = (int) Math.ceil(qtyToConsume / averageDailyUsage);
        return today.plusDays(daysRemaining);
    }
}
