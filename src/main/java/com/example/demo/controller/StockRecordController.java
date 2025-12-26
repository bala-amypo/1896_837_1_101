@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockRecordService stockRecordService;

    public StockController(StockRecordService stockRecordService) {
        this.stockRecordService = stockRecordService;
    }

    @PostMapping("/{productId}/{warehouseId}")
    public ResponseEntity<StockRecord> create(@PathVariable Long productId,
                                              @PathVariable Long warehouseId,
                                              @RequestBody StockRecord sr) {
        return ResponseEntity.ok(stockRecordService.createStockRecord(productId, warehouseId, sr));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(stockRecordService.getStockRecord(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockRecord>> byProduct(@PathVariable Long id) {
        return ResponseEntity.ok(stockRecordService.getRecordsBy_product(id));
    }
}
