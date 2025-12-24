@Service
@RequiredArgsConstructor
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository repo;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    public StockRecord createStockRecord(Long productId, Long warehouseId, StockRecord r) {
        Product p = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Warehouse w = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        repo.findByProductIdAndWarehouseId(productId, warehouseId)
                .ifPresent(x -> { throw new IllegalArgumentException("StockRecord already exists"); });

        r.setProduct(p);
        r.setWarehouse(w);
        r.setLastUpdated(LocalDateTime.now());
        return repo.save(r);
    }

    public StockRecord getStockRecord(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
    }

    public List<StockRecord> getRecordsBy_product(Long productId) {
        return repo.findByProductId(productId);
    }

    public List<StockRecord> getRecordsByWarehouse(Long warehouseId) {
        return repo.findByWarehouseId(warehouseId);
    }
}
