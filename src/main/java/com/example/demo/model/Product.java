@Entity
@Getter @Setter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String productName;
    private String sku;
    private LocalDateTime createdAt;
}
