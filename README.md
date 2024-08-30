API REST com Java e Autenticação JWT
Este projeto é uma API RESTful desenvolvida com Java Spring Boot, utilizando autenticação baseada em JWT (JSON Web Token) para garantir a segurança das rotas.

Pré-requisitos
Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

Java JDK 21
Maven (para gerenciar dependências)
Git (para clonar o repositório)
Postman ou similar (para testar a API)
Docker (opcional, caso deseje usar o Docker para o banco de dados)
Como Clonar e Configurar o Projeto
Clone o Repositório

Abra seu terminal e execute o seguinte comando para clonar o repositório:
  git clone link do repositorio

Navegue até o Diretório do Projeto
  cd nome-do-repositorio

Configure o Banco de Dados

Certifique-se de ter um banco de dados MySQL/PostgreSQL (ou outro de sua escolha) em execução.
Crie um banco de dados para a aplicação.
Configure as credenciais de acesso ao banco no arquivo application.properties ou application.yml, localizado em src/main/resources:
   
    spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update

    
Execute o Projeto!
