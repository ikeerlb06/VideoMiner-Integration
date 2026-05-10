package aiss.videominer.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${videominer.api.key}")
    private String apiKey;

    @Value("${videominer.api.key.enabled:true}")
    private boolean isApiKeyEnabled;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {

        String httpMethod = request.getMethod();

        String authHeader = request.getHeader("Authorization");
        boolean hasValidKey = false;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (apiKey.equals(token)) {
                hasValidKey = true;
            }
        }

        if (isApiKeyEnabled && (httpMethod.equals("POST") || httpMethod.equals("PUT") || httpMethod.equals("DELETE"))) {
            if (!hasValidKey) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("401 Unauthorized: Necesitas una API Key valida para hacer un " + httpMethod);
                return false;
            }
        }

        request.setAttribute("isPremiumUser", hasValidKey);

        return true;
    }
}
