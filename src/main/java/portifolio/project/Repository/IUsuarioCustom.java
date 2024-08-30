package portifolio.project.Repository;

import java.util.List;
import portifolio.project.model.Usuario;

public interface IUsuarioCustom {
	List<Usuario> buscarUsuariosCustomizado(String nome);
}
