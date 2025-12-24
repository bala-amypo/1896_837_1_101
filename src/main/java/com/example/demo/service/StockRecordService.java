public interface StockRecordService {
    StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord r);
    StockRecord getStockRecord(Long id);
    List<StockRecord> getRecordsBy_product(Long productId);
    List<StockRecord> getRecordsByWarehouse(Long warehouseId);
}
