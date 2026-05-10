package aissvideominer.security;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${security.api-key}")
    private String apiKey;

    @Value("${security.api-key-header}")
    private String apiKeyHeader;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getServletPath();

        return path.startsWith("/swagger-ui") ||
                path.startsWith("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String providedKey = request.getHeader(apiKeyHeader);

        if (!apiKey.equals(providedKey)) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            response.getWriter().write(
                    "{ \"error\": \"Unauthorized\", " +
                            "\"message\": \"Missing or invalid API key\" }"
            );

            return;
        }

        filterChain.doFilter(request, response);
    }
}
