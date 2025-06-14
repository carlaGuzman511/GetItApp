package comgetit.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import comgetit.user.User;
import comgetit.user.UserService;
import comgetit.user.dto.UserCredentialsDTO;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String secret;
    private final long expirationTime;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService,
                                   String secret, Long expirationTime) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
        this.userService = userService;
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserCredentialsDTO credentials = mapper
                    .readValue(request.getInputStream(), UserCredentialsDTO.class);
            Optional<User> searchedUser = userService.findUserByEmail(credentials.getEmail());
            if (searchedUser.isEmpty()) {
                invalidCredentials(response);
            }
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            searchedUser.get().getAuthorities())
            );
        } catch (BadCredentialsException exception) {
            invalidCredentials(response);
            throw new RuntimeException();
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(this.secret.getBytes()));
        User user = (User) auth.getPrincipal();
        setUpResponse(response, Map.of("token", token, "id", user.getId()), HttpStatus.OK.value());
    }

    private void invalidCredentials(HttpServletResponse response) {
        try {
            setUpResponse(response, Map.of("message", "El correo o el password son incorrectos"),
                    HttpStatus.FORBIDDEN.value());
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }

    private void setUpResponse(final HttpServletResponse res, final Map<String, Object> values, Integer httpStatus)
            throws IOException {
        res.setStatus(httpStatus);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(new ObjectMapper().writeValueAsString(values));
        res.getWriter().flush();
    }

    @JsonValue
    private Map<String, String> responseBuilder(final String key, final String value) {
        HashMap<String, String> response = new HashMap<>();
        response.put(key, value);
        return response;
    }
}