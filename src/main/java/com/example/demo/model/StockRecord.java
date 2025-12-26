@Entity
@Getter @Setter
@NoArgsConstructor
public class StockRecord {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Warehouse warehouse;

    private Integer quantity;
    private LocalDateTime lastUpdated;
}
