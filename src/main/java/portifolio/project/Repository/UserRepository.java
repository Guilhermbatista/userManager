package portifolio.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import portifolio.project.model.user.User;

public interface UserRepository extends JpaRepository<User, String> {
	UserDetails findByLogin(String login);
}
