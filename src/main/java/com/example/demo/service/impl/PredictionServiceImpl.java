@Service
@RequiredArgsConstructor
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRuleRepository repo;
    private final StockRecordRepository stockRepo;

    public PredictionRule createRule(PredictionRule r) {
        if (r.getAverageDaysWindow() <= 0 || r.getMinDailyUsage() > r.getMaxDailyUsage())
            throw new IllegalArgumentException("Invalid rule");
        r.setCreatedAt(LocalDateTime.now());
        return repo.save(r);
    }

    public List<PredictionRule> getAllRules() {
        return repo.findAll();
    }

    public LocalDate predictRestockDate(Long stockId) {
        stockRepo.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
        return LocalDate.now().plusDays(5);
    }
}
