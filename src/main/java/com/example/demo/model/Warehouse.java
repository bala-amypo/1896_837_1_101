@Entity
@Getter @Setter
@NoArgsConstructor
public class Warehouse {

    @Id @GeneratedValue
    private Long id;

    private String location;
    private LocalDateTime createdAt;
}
