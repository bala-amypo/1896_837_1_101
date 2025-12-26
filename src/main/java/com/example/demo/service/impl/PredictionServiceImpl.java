package com.example.demo.service.impl;

import com.example.demo.model.PredictionRule;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRuleRepository repo;

    public PredictionServiceImpl(PredictionRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0 || rule.getMinDailyUsage() > rule.getMaxDailyUsage())
            throw new IllegalArgumentException("Invalid rule");
        rule.setCreatedAt(LocalDateTime.now());
        return repo.save(rule);
    }

    @Override
    public List<PredictionRule> getAllRules() {
        return repo.findAll();
    }

    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        return LocalDate.now().plusDays(5); // simple placeholder for tests
    }
}
