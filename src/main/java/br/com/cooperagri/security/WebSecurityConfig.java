package br.com.cooperagri.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.cooperagri.security.jwt.AuthFilterToken;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

   // @Autowired
   // private AuthEntryPointJwt unauthorizedHandle;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthFilterToken authFilterToken() {
        return new AuthFilterToken();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable())
               // .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandle))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/users/login", "/users/cadastrar").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/swagger-ui/index.html").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated());

        // Desabilitar headers de frameOptions para permitir o H2 console funcionar
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        http.addFilterBefore(authFilterToken(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
