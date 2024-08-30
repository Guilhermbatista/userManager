package portifolio.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import portifolio.project.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByEmail(String email);
}
