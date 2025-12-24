@Service
@RequiredArgsConstructor
public class ConsumptionLogServiceImpl implements ConsumptionLogService {

    private final ConsumptionLogRepository repo;
    private final StockRecordRepository stockRepo;

    public ConsumptionLog logConsumption(Long stockId, ConsumptionLog log) {
        StockRecord sr = stockRepo.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));

        if (log.getConsumedDate() != null && log.getConsumedDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("consumedDate cannot be future");

        log.setStockRecord(sr);
        return repo.save(log);
    }

    public List<ConsumptionLog> getLogsByStockRecord(Long stockId) {
        return repo.findByStockRecordId(stockId);
    }

    public ConsumptionLog getLog(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConsumptionLog not found"));
    }
}
