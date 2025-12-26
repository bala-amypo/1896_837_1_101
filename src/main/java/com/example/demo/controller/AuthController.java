@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody Map<String, String> req) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, String> req) {
        return ResponseEntity.status(401).build();
    }
}
