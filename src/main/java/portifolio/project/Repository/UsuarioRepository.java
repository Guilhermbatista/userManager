package portifolio.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import portifolio.project.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, IUsuarioCustom {

    @Override
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
    List<Usuario> buscarUsuariosCustomizado(@Param("nome") String nome);
}

