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
│   │    │    ├── DadosDetalhamentoResumidoResposta.java  ## Comentado - provável exclusão
│   │    │    ├── DadosListagemTotalResposta.java
│   │    │    └── DadosListagemUnicoResposta.java  ## Comentado - provável exclusão
│   │    ├── token
│   │    │    └── DadosTokenJWT.java
│   │    ├── topico
│   │    │    └── validar
│   │    │    │    └── DadosValidarResposta.java
│   │    │    ├── DadosAtualizacaoTopico.java
│   │    │    ├── DadosCadastroTopico.java
│   │    │    ├── DadosDetalhamentoResumidoTopico.java
│   │    │    ├── DadosDetalhamentoTopico.java  ## Comentado - provável exclusão
│   │    │    ├── DadosListagemTotalTopico.java
│   │    │    └── DadosListagemUnicoTopico.java
│   │    └── usuario
│   │    │    └── DadosAtualizacaoUsuario.java
│   │    │    ├── DadosCadastroUsuario.java
│   │    │    ├── DadosDetalhamentoUsuario.java
│   │    │    └── DadosListagemUsuario.java 
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
│   │    ├── UsuarioLogadoService.java 
│   │    └── UsuarioService.java
│   └── ApiApplication.java
└── README.md
```

---
