<h1 align="center">🗣️📚 FórumHub — API REST com Spring Boot 3, MySQL e JWT</h1>

<p align="center">
  <a href="https://www.alura.com.br">
    <img src="https://img.shields.io/badge/Alura-Challenge-0074c1?style=for-the-badge&logo=alura&logoColor=white">
  </a>
  <img src="https://img.shields.io/badge/status-Concluído-brightgreen?style=for-the-badge">
</p>

<p align="center">
  <!-- Linguagem e Framework -->
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3-green?style=for-the-badge&logo=springboot"/>
  <br>
  <!-- Banco e Versionamento -->
  <img src="https://img.shields.io/badge/MySQL-8-blue?style=for-the-badge&logo=mysql"/>
  <img src="https://img.shields.io/badge/Flyway-Versioning-red?style=for-the-badge&logo=flyway"/>
  <br>
  <!-- Segurança e Documentação -->
  <img src="https://img.shields.io/badge/JWT-enabled-orange?style=for-the-badge&logo=jwt"/>
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger"/>
  <br>
  <!-- Build e Licença -->
  <img src="https://img.shields.io/badge/Maven-Build-orange?style=for-the-badge&logo=apachemaven"/>
  <img src="https://img.shields.io/badge/license-MIT-blue.svg?style=for-the-badge">
</p>

---

<h2 align="center">📖 Sobre o Projeto</h2>

O **FórumHub** é uma **API REST** desenvolvida como desafio da formação Back-End Java Orientado a Objetos - GB/ONE da [Alura](https://www.alura.com.br). 
Simula o back-end de um fórum de discussão com autenticação, controle de usuários, categorias, tópicos, respostas, cursos e controle de permissões.
O projeto adota arquitetura em camadas (controller, service, repository, domain e DTOs), aplicando boas práticas de organização, responsabilidade e manutenção de código.

Este projeto proporciona prática em:
- Boas práticas com [Spring Boot](https://spring.io/projects/spring-boot)
- Estruturação de API REST
- Validações
- Tratamento de erros
- Documentação com [Swagger (OpenAPI)](https://swagger.io/specification/)
- Segurança com [JWT (JSON Web Token)](https://jwt.io/)

---

<h2 align="center">⚙️ Tecnologias Utilizadas</h2>

- [Java 17](https://www.java.com/pt-BR/) ou superior + [Spring Boot 3](https://start.spring.io/)
- [Spring Security](https://spring.io/projects/spring-security) + [JWT (JSON Web Token)](https://jwt.io/)
- [JPA](https://spring.io/projects/spring-data-jpa) + Hibernate
- Banco de dados [MySQL](https://www.mysql.com/)
- Controle de versionamento de banco com [Flyway](https://flywaydb.org/)
- [Maven](https://maven.apache.org/): Gerenciamento de dependências e build.
- [Swagger (OpenAPI)](https://swagger.io/specification/)

---

<h2 align="center">🧰 Ferramentas Utilizadas</h2>

- 💻 [IntelliJ IDE](https://www.jetbrains.com/pt-br/idea/#): Ambiente de desenvolvimento integrado.
- 💾 [MySQL](https://www.mysql.com/): Sistema de gerenciamento de banco de dados relacional de código aberto.

---

<h2 align="center">🔄 Migrations e Versionamento de Banco</h2>

O projeto utiliza o [Flyway](https://flywaydb.org/) para gerenciar as **migrations de banco de dados** no [MySQL](https://www.mysql.com/). Todas as alterações de estrutura no banco (como criação de tabelas e alterações de schema) são versionadas e controladas, garantindo consistência entre os ambientes de desenvolvimento e produção.


---

<h2 align="center">📦 Organização do Projeto</h2>

```plaintext
├── hub.forum.api 
│   ├── controller
│   │    └── AutenticacaoController.java
│   │    ├── CursoController.java
│   │    ├── PerfilController.java
│   │    ├── RespostaController.java
│   │    ├── TopicoController.java
│   │    └── UsuarioController.java
│   ├── domain
│   │    └── categoria
│   │    │    └── Categoria.java
│   │    ├── curso
│   │    │    └── Curso.java
│   │    ├── perfil
│   │    │    └── Perfil.java
│   │    ├── resposta
│   │    │    └── Resposta.java
│   │    ├── topico
│   │    │    └── StatusTopico.java
│   │    │    └── Topico.java
│   │    └── usuario
│   │    │    └── Usuario.java
│   ├── dto
│   │    └── curso
│   │    │    └── DadosCadastroCurso.java
│   │    │    ├── DadosDetalhamentoCurso.java
│   │    │    └── DadosListagemCurso.java
│   │    ├── perfil
│   │    │    └── DadosCadastroPerfil.java
│   │    │    ├── DadosDetalhamentoPerfil.java
│   │    │    └── DadosListagemPerfil.java
│   │    ├── resposta
│   │    │    └── DadosAtualizacaoResposta.java
│   │    │    ├── DadosCadastroResposta.java
│   │    │    ├── DadosDetalhamentoResposta.java
│   │    │    └── DadosListagemTotalResposta.java
│   │    ├── token
│   │    │    └── DadosTokenJWT.java
│   │    ├── topico
│   │    │    └── validar
│   │    │    │    └── DadosValidarResposta.java
│   │    │    ├── DadosAtualizacaoTopico.java
│   │    │    ├── DadosCadastroTopico.java
│   │    │    ├── DadosDetalhamentoResumidoTopico.java
│   │    │    ├── DadosListagemTotalTopico.java
│   │    │    └── DadosListagemUnicoTopico.java
│   │    └── usuario
│   │    │    └── DadosCadastroUsuario.java
│   │    │    ├── DadosDetalhamentoUsuario.java
│   │    │    ├── DadosListagemUsuario.java
│   │    │    └── DadosLogin.java 
│   ├── infra 
│   │    └── exception
│   │    │    └── TratadorDeErros.java
│   │    │    └── ValidacaoException.java
│   │    ├── security
│   │    │    └── SecurityConfigurations.java
│   │    │    └── SecurityFilter.java
│   │    ├── springdoc
│   │    │    └── SpringDocConfigurations.java 
│   ├── repository 
│   │    ├── CursoRepository.java
│   │    ├── PerfilRepository.java
│   │    ├── RespostaRepository.java
│   │    ├── TopicoRepository.java
│   │    └── UsuarioRepository.java
│   ├── service
│   │    └── AutenticacaoService.java
│   │    ├── CursoService.java
│   │    ├── PerfilService.java
│   │    ├── RespostaService.java
│   │    ├── TokenService.java
│   │    ├── TopicoService.java 
│   │    └── UsuarioService.java
│   └── ApiApplication.java
└── README.md
```
---

<h2 align="center">🚀 Como Executar o Projeto</h2>

### Pré-requisitos:
- [Java 17](https://www.java.com/pt-BR/) ou superior
- [MySQL](https://www.mysql.com/) instalado
- IDE de sua preferência ([IntelliJ IDE](https://www.jetbrains.com/pt-br/idea/#), [VSCode](https://code.visualstudio.com/), [Eclipse](https://eclipseide.org/) etc.)

### Passo a passo:
1. Clone o repositório
```bash
git clone https://github.com/renancvitor/challenge-forum-hub-api.git
```
2. Acesse a pasta do projeto
```bash
cd challenge-forum-hub-api
```
3. Configure o banco de dados no arquivo `application.properties` com suas credenciais locais. Ao iniciar o projeto, as migrations serão aplicadas automaticamente pelo [Flyway](https://flywaydb.org/).
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
4. Execute o projeto com o Maven Wrapper:
```bash
./mvnw spring-boot:run
```

---

<h2 align="center">🔑 Acesso à API</h2>

- Acesse o Swagger em:

```bash
http://localhost:8080/swagger-ui/index.html
```

- Faça login para obter o token [JWT](https://jwt.io/) e utilizar nas requisições protegidas.

---

<h2 align="center">✨ Funcionalidades</h2>

O **FórumHub** é um back-end de fórum desenvolvido com [Spring Boot](https://spring.io/projects/spring-boot), com foco em boas práticas e organização de **API REST**.

### 🔐 Autenticação e Segurança
- Cadastro e login de usuários
- Autenticação via [JWT (JSON Web Token)](https://jwt.io/)
- Controle de acesso baseado em perfis de usuário

### 📚 **Gerenciamento de Conteúdo**
- **Cursos**
  - Cadastro de cursos
  - Listagem de cursos
- **Categorias**
  - Organização dos tópicos por categorias
- **Tópicos de Discussão**
    - Criar novos tópicos
    - Listar tópicos (com paginação e filtros)
    - Atualizar e excluir tópicos
    - Controle de status dos tópicos
    - Marcar uma resposta como solução
- **Respostas aos Tópicos**
    - Adicionar respostas aos tópicos
    - Listar respostas (com paginação e filtros)
    - Atualizar e excluir respostas

### 🛠️ **Validações e Tratamento de Erros**
- Validação de dados de entrada (DTOs com Bean Validation)
- Mensagens de erros claras e padronizadas
- Tratamento centralizado de exceções

### 📊 **Documentação**
- API documentada com [Swagger UI](https://swagger.io/specification/)

---

<h2 align="center">📢 Agradecimento</h2>

Agradeço à [Alura](https://www.alura.com.br) por essa oportunidade de aprendizado contínuo e por incentivar o desenvolvimento de habilidades de programação de forma estruturada e desafiadora. 🚀
Este projeto consolidou habilidades práticas em desenvolvimento de APIs RESTful robustas, autenticação segura com [Spring Security](https://spring.io/projects/spring-security) e [JWT](https://jwt.io/), tratamento global de exceções e versionamento controlado de banco com Flyway — competências amplamente aplicadas em desenvolvimento back-end profissional.

---

<h2 align="center">🤝 Contribuições</h2>

Se você quiser contribuir para o projeto, siga estas etapas:

1. Faça um fork deste repositório.
2. Crie uma nova branch (`git checkout -b feature/alguma-coisa`).
3. Faça suas mudanças.
4. Envie um pull request explicando as mudanças realizadas.

Obrigado pelo interesse em contribuir!

---

<h2 align="center">📫 Contato</h2>

Se tiver dúvidas ou sugestões, sinta-se à vontade para entrar em contato:

- 📧 **E-mail**: [renan.vitor.cm@gmail.com](mailto:renan.vitor.cm@gmail.com)
- 🐦 **GitHub**: [renancvitor](https://github.com/renancvitor)
- 🟦 **LinkedIn**: [Renan Vitor](https://www.linkedin.com/in/renan-vitor-developer/)

---

<h2 align="center">📄 Licença</h2>

📌 Este projeto está licenciado sob a [Licença MIT](LICENSE), permitindo sua utilização, modificação e distribuição de forma livre, conforme os termos descritos.
