public interface ConsumptionLogService {
    ConsumptionLog logConsumption(Long stockId, ConsumptionLog log);
    List<ConsumptionLog> getLogsByStockRecord(Long stockId);
    ConsumptionLog getLog(Long id);
}
