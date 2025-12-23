// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtProvider jwtProvider;
//     private final CustomUserDetails customUserDetails;

//     public JwtAuthenticationFilter(JwtProvider jwtProvider,
//                                    CustomUserDetails customUserDetails) {
//         this.jwtProvider = jwtProvider;
//         this.customUserDetails = customUserDetails;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String header = request.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);

//             if (jwtProvider.validateToken(token)) {
//                 String email = jwtProvider.getEmailFromToken(token);

//                 UserDetails loadedUser = customUserDetails.loadUserByUsername(email);

//                 UsernamePasswordAuthenticationToken authentication =
//                         new UsernamePasswordAuthenticationToken(
//                                 loadedUser,
//                                 null,
//                                 loadedUser.getAuthorities()
//                         );

//                 authentication.setDetails(
//                         new WebAuthenticationDetailsSource().buildDetails(request)
//                 );

//                 SecurityContextHolder.getContext().setAuthentication(authentication);
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }
