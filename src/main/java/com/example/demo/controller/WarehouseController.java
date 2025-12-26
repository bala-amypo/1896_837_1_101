@RestController("warehouseController")
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<Warehouse> create(@RequestBody Warehouse w) {
        return ResponseEntity.ok(warehouseService.createWarehouse(w));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouse(id));
    }
}
