public interface PredictionService {
    LocalDate predictRestockDate(Long stockId);
    List<PredictionRule> getAllRules();
    PredictionRule createRule(PredictionRule rule);
}
