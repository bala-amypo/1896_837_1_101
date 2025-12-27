public interface StockRecordService {
    StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord record);
    StockRecord getStockRecord(Long id);
    List<StockRecord> getRecordsBy_product(Long productId); // Matches Test Name
    List<StockRecord> getRecordsByWarehouse(Long warehouseId);
}