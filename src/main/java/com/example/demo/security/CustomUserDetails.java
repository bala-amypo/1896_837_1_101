@Service
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = repo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Not found"));

       return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(
            user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList()
        )
        .build();

    }
}
