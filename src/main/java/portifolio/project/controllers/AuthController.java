package portifolio.project.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import portifolio.project.DTO.LoginRequestDTO;
import portifolio.project.DTO.RegisterRequestDTO;
import portifolio.project.DTO.ResponseDTO;
import portifolio.project.Repository.UserRepository;
import portifolio.project.model.User;
import portifolio.project.model.Usuario;
import portifolio.project.securty.TokenService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO body) {
		User user = this.repository.findByEmail(body.email())
				.orElseThrow(() -> new RuntimeException("User not found Controller"));
		if (passwordEncoder.matches(body.senha(), user.getPassword())) {
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody RegisterRequestDTO body) {

		Optional<User> user = this.repository.findByEmail(body.email());

		if (user.isEmpty()) {
			User newUser = new User();
			newUser.setPassword(passwordEncoder.encode(body.senha()));
			newUser.setEmail(body.email());
			newUser.setName(body.nome());
			this.repository.save(newUser);

			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));

		}

		return ResponseEntity.badRequest().build();
	}
	
}
