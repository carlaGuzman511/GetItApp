package comgetit.filters;

import comgetit.exception.GetItHandleException;
import comgetit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    @Autowired
    public WebSecurity(final UserService userService
        , final BCryptPasswordEncoder cryptPasswordEncoder) {
        this.userService = userService;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/register-user").permitAll()
                .antMatchers(HttpMethod.GET, "/workareas").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(
                        authenticationManager(), userService, secret, expirationTime))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), secret, userService))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(cryptPasswordEncoder);
    }
}