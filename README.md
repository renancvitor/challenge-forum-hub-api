<h1 align="center"> 💬 FórumHub — API REST com Spring Boot 3, MySQL e JWT<br> (<a href="https://www.alura.com.br">Alura - Challenge</a>)<br>
  <img src="https://img.shields.io/badge/Status-Concluído-brightgreen" width="150" height="30" />
</h1>

---

<h2 align="center">📖 Visão Geral do Projeto</h2>

**FórumHub** é uma **API REST** completa, segura e totalmente documentada para um sistema de fórum online, com autenticação via [JWT](https://jwt.io/), validação robusta e arquitetura profissional com [Spring Boot](https://spring.io/projects/spring-boot).
Desenvolvido como parte do Challenge da formação Back-End Java OOP da [Alura](https://www.alura.com.br).
O projeto adota arquitetura em camadas (controller, service, repository, domain e DTOs), aplicando boas práticas de organização, responsabilidade e manutenção de código.

O desenvolvimento do projeto consolidou habilidades como:
- ⚙️ Arquitetura RESTful
- 🛠️ Testes unitários e de integração com JUnit e Mockito
- ✅ Validações robustas com Bean Validation
- 🚨 Tratamento de erros
- 📚 Documentação automatizada com [Swagger (OpenAPI)](https://swagger.io/specification/)
- 🔐 Segurança com [JWT (JSON Web Token)](https://jwt.io/)

---

<h2 align="center">⚙️ Tecnologias Utilizadas</h2>

- ☕ [Java 17](https://www.java.com/pt-BR/) ou superior + 🌱 [Spring Boot 3](https://start.spring.io/)
- 🔐 [Spring Security](https://spring.io/projects/spring-security) + 🧾 [JWT](https://jwt.io/)
- 📦 [JPA](https://spring.io/projects/spring-data-jpa) + 🐘 [Hibernate](https://hibernate.org/)
- 🐬 Banco de dados [MySQL](https://www.mysql.com/)
- 🛠️ Controle de versionamento de banco com [Flyway](https://flywaydb.org/)
- 📦 [Maven](https://maven.apache.org/): Gerenciamento de dependências e build.
- 📦 [JUnit 5](https://junit.org/)
- 🔧 [Mockito](https://site.mockito.org/)
- 📄 [Swagger (OpenAPI)](https://swagger.io/specification/)

---

<h2 align="center">🧰 Ferramentas Utilizadas</h2>

- 💻 [IntelliJ IDE](https://www.jetbrains.com/pt-br/idea/#): Ambiente de desenvolvimento integrado.
- 💾 [MySQL](https://www.mysql.com/): Sistema de gerenciamento de banco de dados relacional de código aberto.

---

<h2 align="center">🔄 Migrations e Versionamento de Banco</h2>

O projeto utiliza o [Flyway](https://flywaydb.org/) para gerenciar as **migrations de banco de dados** no [MySQL](https://www.mysql.com/). Todas as alterações de estrutura no banco (como criação de tabelas e alterações de schema) são versionadas e controladas, garantindo consistência entre os ambientes de desenvolvimento e produção.

---

<h2 align="center">✨ Funcionalidades</h2>

O **FórumHub** é um back-end de fórum desenvolvido com [Spring Boot](https://spring.io/projects/spring-boot), com foco em boas práticas e organização de **API REST**.

### 🔐 Autenticação e Segurança
- Cadastro e login de usuários
- Autenticação via [JWT](https://jwt.io/)
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

<h2 align="center">🧪 Testes Automatizados</h2>

O projeto conta com uma **cobertura significativa de testes unitários e de integração**, garantindo a qualidade e o correto funcionamento dos fluxos principais de negócio da API, incluindo:
- Cadastro de usuários, perfis, cursos, tópicos e respostas.
- Autenticação com [JWT](https://jwt.io/).
- Validações de regras de negócio.
- Tratamento global de exceções.

**Os testes foram desenvolvidos com:**
- 📦 [JUnit 5](https://junit.org/junit5/)
- 🔧 [Mockito](https://site.mockito.org/)
- 🎯 [Spring Boot Test](https://docs.spring.io/spring-security/reference/servlet/test/index.html)

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

## 🧪 Cobertura de Testes Automatizados

Os testes estão organizados por módulo e divididos em cenários de sucesso (acertivos) e erro:

```plaintext
test
└── hub.forum.api
├── controller
|   ├── resposta
│   │   ├── acertivos
│   │   │   ├── RespostaControllerAtualizarTest.java
│   │   │   ├── RespostaControllerDeletarRespostaTest.java
│   │   │   └── RespostaControllerListarTest.java
│   │   └── erros
│   │       ├── RespostaControllerAtualizarTestERRO.java
│   │       └── RespostaControllerDeletarRespostaTestERRO.java
│   ├── topico
│   │   ├── acertivos
│   │   │   ├── TopicoControllerAtualizarTest.java
│   │   │   ├── TopicoControllerDeletarTopicoTest.java
│   │   │   ├── TopicoControllerListarByIdTest.java
│   │   │   ├── TopicoControllerListarTest.java
│   │   │   └── TopicoControllerMarcarSolucaoTest.java
│   │   └── erros
│   │       ├── TopicoControllerAtualizarTestERRO.java
│   │       ├── TopicoControllerDeletarTopicoTestERRO.java
│   │       └── TopicoControllerMarcarSolucaoTestERRO.java
│   ├── CadastroControllersTestErro400.java
│   └── CadastroControllersTestOk200.java
├── service
│   ├── resposta
│   │   ├── acertivos
│   │   │   ├── RespostaServiceAtualizarTest.java
│   │   │   ├── RespostaServiceDeletarRespostaTest.java
│   │   │   └── RespostaServiceMarcarSolucaoTest.java
│   │   └── erros
│   │       ├── RespostaServiceAtualizarTestERRO.java
│   │       ├── RespostaServiceDeletarRespostaTestERRO.java
│   │       └── RespostaServiceMarcarSolucaoTestERRO.java
│   ├── topico
│   │   ├── acertivos
│   │   │   ├── TopicoServiceAtualizarTest.java
│   │   │   ├── TopicoServiceDeletarTopicoTest.java
│   │   │   └── TopicoServiceListarByIdTest.java
│   │   └── erros
│   │       ├── TopicoServiceAtualizarTestERRO.java
│   │       ├── TopicoServiceDeletarTestERRO.java
│   │       └── TopicoServiceListarByIdTestERRO.java
|   └── CadastroCompletoTest.java
```
---

<h2 align="center">🚀 Como Executar o Projeto</h2>

### Pré-requisitos:
- ☕ [Java 17](https://www.java.com/pt-BR/) ou superior
- 🐬 [MySQL](https://www.mysql.com/) instalado
- 💻 IDE de sua preferência ([IntelliJ IDE](https://www.jetbrains.com/pt-br/idea/#), [VSCode](https://code.visualstudio.com/), [Eclipse](https://eclipseide.org/) etc.)

### Passo a passo:
1. Clone o repositório
```bash
git clone https://github.com/renancvitor/challenge-forum-hub-api.git
```
2. Acesse a pasta do projeto
```bash
cd challenge-forum-hub-api
```
3. Configure o banco de dados no arquivo `src/main/resources/application.properties` com suas credenciais locais. Ao iniciar o projeto, as migrations serão aplicadas automaticamente pelo [Flyway](https://flywaydb.org/).
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
- 🟦 **LinkedIn**: [Renan Vitor](https://www.linkedin.com/in/renan-vitor-developer/)

---

<h2 align="center">📄 Licença</h2>

📌 Este projeto está licenciado sob a [Licença MIT](LICENSE), o  que significa que você pode utilizá-lo, modificar, compartilhar e distribuir livremente, desde que mantenha os devidos créditos aos autores e inclua uma cópia da licença original.
