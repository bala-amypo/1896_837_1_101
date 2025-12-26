@RestController
@RequestMapping("/api/consumption")
public class ConsumptionController {

    private final ConsumptionLogService service;

    public ConsumptionController(ConsumptionLogService service) {
        this.service = service;
    }

    @PostMapping("/{stockId}")
    public ResponseEntity<ConsumptionLog> log(@PathVariable Long stockId, @RequestBody ConsumptionLog log) {
        return ResponseEntity.ok(service.logConsumption(stockId, log));
    }

    @GetMapping("/record/{id}")
    public ResponseEntity<List<ConsumptionLog>> logs(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLogsByStockRecord(id));
    }
}
