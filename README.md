<h1 align="center">🗣️📚 FórumHub — API REST de Fórum (<a href="https://www.alura.com.br">Alura - Challenge</a>) 🎯<br>
  <img src="https://img.shields.io/badge/status-Concluído-brightgreen">
</h1>

---

<h2 align="center">✅ Projeto concluído</h2>

Este projeto faz parte da formação **Back-End Java Orientado a Objetos - GB/ONE**, da [Alura](https://www.alura.com.br).

---

<h2 align="center">📖 Sobre o Projeto</h2>

O **FórumHub** é uma **API REST** desenvolvida como desafio proposto pela plataforma [Alura](https://www.alura.com.br).
Simula o back-end de um fórum de discussão com autenticação, controle de usuários, categorias, tópicos, respostas, cursos e controle de permissões. 

Projeto ideal para praticar:
- Boas práticas com [Spring Boot](https://spring.io/projects/spring-boot)
- Estruturação de API REST
- Validações
- Tratamento de erros
- Documentação com [Swagger (OpenAPI)](https://swagger.io/specification/)
- Segurança com [JWT (JSON Web Token)](https://jwt.io/)

---

<h2 align="center">⚙️ Tecnologias Utilizados</h2>

- [Java 17](https://www.java.com/pt-BR/) ou superior + [Spring Boot 3](https://start.spring.io/)
- [Spring Security](https://spring.io/projects/spring-security) + [JWT (JSON Web Token)](https://jwt.io/)
- [JPA](https://spring.io/projects/spring-data-jpa) + Hibernate
- Banco de dados [MySQL](https://www.mysql.com/)
- Ferramenta de automação e gerenciamento de projetos: Maven
- [Swagger (OpenAPI)](https://swagger.io/specification/)

---

<h2 align="center">🧰 Ferramentas Utilizadas</h2>

- 💻 [IntelliJ IDE](https://www.jetbrains.com/pt-br/idea/#): Ambiente de desenvolvimento integrado.
- 🐘 [MySQL](https://www.mysql.com/): Sistema de gerenciamento de banco de dados relacional de código aberto.

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
- [MySQL](https://www.mysql.com/) instalado (ou outro banco configurado)
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
3. Configure o banco de dados no arquivo application.properties ou application.yml com as credenciais locais:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
4. Execute o projeto:
```bash
./mvnw spring-boot:run
```

---

<h2 align="center">🔑 Acesso à API</h2>

- Acesso o Swagger em:

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
- Categorias de Tópicos
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
