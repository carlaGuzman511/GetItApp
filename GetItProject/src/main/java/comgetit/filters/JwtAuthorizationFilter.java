package comgetit.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import comgetit.user.User;
import comgetit.user.UserService;
import comgetit.filters.exception.InvalidUserTokenException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final String secret;
    private final UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authManager, String secret,
        UserService userService) {
        super(authManager);
        this.secret = secret;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (TokenExpiredException exception) {
            setUpResponse("Su session ha expirado", HttpStatus.FORBIDDEN.value(), response);
        } catch (InvalidUserTokenException exception) {
            setUpResponse("No puede acceder a este recurso", HttpStatus.FORBIDDEN.value(), response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token == null) {
            throw new InvalidUserTokenException();
        }
        String email = JWT.require(Algorithm.HMAC512(secret.getBytes()))
            .build()
            .verify(token.replace(TOKEN_PREFIX, ""))
            .getSubject();
        if (email == null) {
            throw new InvalidUserTokenException();
        }
        User user = userService.findUserByEmail(email).get();
        return new UsernamePasswordAuthenticationToken(email, user, user.getAuthorities());
    }

    private void setUpResponse(final String message, int responseCode,
        HttpServletResponse response) throws IOException {
        Map<String, String> answer = new HashMap<>();
        answer.put("message", message);
        response.setStatus(responseCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(answer));
    }
}