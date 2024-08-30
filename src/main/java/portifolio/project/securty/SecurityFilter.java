package portifolio.project.securty;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portifolio.project.Repository.UserRepository;
import portifolio.project.model.User;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;
	@Autowired
	UserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = this.recoverToken(request);
		var login = tokenService.validateToken(token);

		if (login != null) {
			User user = repository.findByEmail(login)
					.orElseThrow(() -> new RuntimeException("User Not Found"));
			// possivel erro no futuro
			var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

			var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);

	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null)
			return null;
		return authHeader.replaceAll("Bearer ", "");
	}

}
