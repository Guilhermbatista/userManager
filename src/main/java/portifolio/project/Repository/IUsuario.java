package portifolio.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import portifolio.project.model.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer>, IUsuarioCustom {
	@Query(value="SELECT * FROM Usuarios p WHERE p.nome LIKE %:nome%;", nativeQuery = true)
	List<Usuario> findByNameContaining(@Param("nome")String nome);

}
