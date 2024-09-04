package portifolio.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotBlank(message = "O nome é Obrigatorio")
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	@Email(message = "Insira um email válido")
	@NotBlank(message = "O email é Obrigatorio")
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	@NotBlank(message = "A senha é Obrigatorio")
	@Column(name = "senha", columnDefinition = "TEXT", nullable = false)
	private String senha;
	@NotBlank(message = "O Telefone é Obrigatorio")
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;

	
}
