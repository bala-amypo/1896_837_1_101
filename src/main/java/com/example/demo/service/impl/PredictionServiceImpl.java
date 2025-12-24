package com.example.demo.service.impl;

import com.example.demo.model.PredictionRule;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.service.PredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRuleRepository repo;

    public PredictionServiceImpl(PredictionRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public PredictionRule create(PredictionRule rule) {
        return repo.save(rule);
    }

    @Override
    public List<PredictionRule> getActiveRules(LocalDate date) {
        return repo.findAll();
    }
}
