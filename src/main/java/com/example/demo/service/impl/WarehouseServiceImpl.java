@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository repo;

    public Warehouse createWarehouse(Warehouse w) {
        if (w.getLocation() == null || w.getLocation().isBlank())
            throw new IllegalArgumentException("location required");
        w.setCreatedAt(LocalDateTime.now());
        return repo.save(w);
    }

    public Warehouse getWarehouse(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

    public List<Warehouse> getAllWarehouses() {
        return repo.findAll();
    }
}
