package portifolio.project.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import portifolio.project.DTO.RegisterRequestDTO;
import portifolio.project.Repository.UserRepository;
import portifolio.project.model.User;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@DisplayName("Should get User successfelly from bb")
	void findByEmailSuccess() {
		RegisterRequestDTO data = new RegisterRequestDTO("Guilherme","guilherme@santos.com","123456789");
		this.createUser(data);
		
		Optional<User> foundUser = this.userRepository.findByEmail(data.email());
		
		assertThat(foundUser.isPresent()).isTrue();
		
	}
	
	@Test
	@DisplayName("Should not get User from BB when user not exists")
	void findByEmailSuccessCase2() {		
		RegisterRequestDTO data = new RegisterRequestDTO("Guilherme","guilherme@santos.com","123456789");
		
		Optional<User> foundUser = this.userRepository.findByEmail(data.email());
		
		assertThat(foundUser.isEmpty()).isTrue();
		
	}
	
	
	private User createUser(RegisterRequestDTO data) {
		User newUser = new User(data);
		this.entityManager.persist(newUser);
		return newUser;
	}
}
