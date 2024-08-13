package portifolio.project.securty;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityService {

	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
	    return http
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(authorize -> authorize
	                    .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
	                    .anyRequest().authenticated()
	            )
	            .build();
	    }
}
