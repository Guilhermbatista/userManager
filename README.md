# API REST para Gerenciamento de Usuários
Descrição
Esta API REST, desenvolvida utilizando Spring Boot, é projetada para fornecer uma interface robusta para o gerenciamento de usuários em um sistema. Ela oferece operações CRUD (Create, Read, Update, Delete) para a entidade Usuário, permitindo que clientes possam criar, visualizar, atualizar e deletar registros de usuários.

<h1>Funcionalidades Principais</h1> 
Criação de Usuários: Endpoint para adicionar novos usuários ao sistema.
Listagem de Usuários: Retorna todos os usuários cadastrados, com suporte para paginação e ordenação.
Consulta por ID: Busca os detalhes de um usuário específico através do ID.
Atualização de Usuários: Permite modificar as informações de um usuário existente.
Exclusão de Usuários: Remove um usuário do sistema.
Busca Personalizada: Filtros para pesquisa de usuários por nome, email ou telefone.<br>
 <h1>Tecnologias Utilizadas</h1>
Spring Boot: Framework principal para desenvolvimento da API.
Spring Data JPA: Para abstração do acesso ao banco de dados.
Hibernate: Implementação JPA padrão utilizada para ORM.
<br>
Spring Security (em implementação): Para autenticação e autorização dos endpoints.
Swagger/OpenAPI (em implementação): Para documentação interativa da API.
<h1>Estrutura do Projeto</h1>
Entidade Usuário: Modelo que representa os usuários no banco de dados.
Repositório: Interface que define as operações de banco de dados utilizando Spring Data JPA.
Serviço: Camada intermediária que contém a lógica de negócios.
Controlador: Endpoints REST que expõem a funcionalidade da API aos clientes.<br>
<h2> Fase Atual<br></h2>
⚠️ Este projeto está em fase de atualização e implementação de novas tecnologias.

Estamos integrando Spring Security para melhorar a segurança da API.
A documentação interativa via Swagger/OpenAPI está em desenvolvimento.
Melhorias na estrutura do código e na performance estão sendo realizadas.
