package portifolio.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import portifolio.project.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, IUsuarioCustom {
	
	@Override
	@Query("SELECT u FROM usuarios u WHERE u.nome =:nome")
	default List<Usuario> buscarUsuariosCustomizado(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
