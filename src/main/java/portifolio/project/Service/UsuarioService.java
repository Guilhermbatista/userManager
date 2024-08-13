package portifolio.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import portifolio.project.Repository.IUsuario;
import portifolio.project.model.Usuario;

@Service
public class UsuarioService {

	private IUsuario repository;
	private PasswordEncoder passwordEncoder;

	public UsuarioService(IUsuario repository) {
		super();
		this.repository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public List<Usuario> ListUsuarios() {
		List<Usuario> list = repository.findAll();
		return list;
	}

	public List<Usuario> buscarUsuarioPorNome(String nome) {
		return repository.buscarUsuariosCustomizado(nome);
	}

	public Optional<Usuario> UsuarioId(Integer id) {
		return repository.findById(id);
	}

	public Usuario CriarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		return repository.save(usuario);
	}

	public Usuario EditarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		return repository.save(usuario);
	}

	public Boolean deleteUsuario(Integer id) {
		repository.deleteById(id);
		return true;
	}

	public Boolean ValidarSenha(Usuario usuario) {
		String senha = repository.getReferenceById(usuario.getId()).getSenha();

		return passwordEncoder.matches(usuario.getSenha(), senha);
	}
}
