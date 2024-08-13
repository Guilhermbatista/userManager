package portifolio.project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import portifolio.project.Service.UsuarioService;
import portifolio.project.model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioControler {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listUsuario() {
		return ResponseEntity.status(200).body(usuarioService.ListUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> usuarioID(@PathVariable Integer id) {
		return ResponseEntity.status(200).body(usuarioService.UsuarioId(id));
	}

	@GetMapping("/busca")
	public List<Usuario> buscarUsuario(@RequestParam(required = false) String nome) {
		return usuarioService.buscarUsuarioPorNome(nome);
	}

	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(201).body(usuarioService.CriarUsuario(usuario));
	}

	@PutMapping
	public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(201).body(usuarioService.EditarUsuario(usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
		usuarioService.deleteUsuario(id);
		return ResponseEntity.status(204).build();

	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> ValidarSenha(@Valid @RequestBody Usuario usuario) {
		Boolean valid = usuarioService.ValidarSenha(usuario);
		if (!valid) {
			System.out.println("Buceta");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.status(200).build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		;

		return errors;
	}

}
