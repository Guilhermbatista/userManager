package portifolio.project.Repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import portifolio.project.model.Usuario;

public class IUsuarioImpl implements IUsuarioCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Usuario> buscarUsuariosCustomizado(String nome) {
		String jpql = "SELECT u FROM Usuario u WHERE u.nome LIKE :nome";
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

}
