package hub.forum.api.controller;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.curso.DadosCadastroCurso;
import hub.forum.api.dto.curso.DadosDetalhamentoCurso;
import hub.forum.api.dto.perfil.DadosCadastroPerfil;
import hub.forum.api.dto.perfil.DadosDetalhamentoPerfil;
import hub.forum.api.dto.resposta.DadosCadastroResposta;
import hub.forum.api.dto.resposta.DadosDetalhamentoResposta;
import hub.forum.api.dto.topico.DadosCadastroTopico;
import hub.forum.api.dto.topico.DadosDetalhamentoResumidoTopico;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import hub.forum.api.dto.usuario.DadosDetalhamentoUsuario;
import hub.forum.api.repository.PerfilRepository;
import hub.forum.api.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CadastroControllersTestOk200 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosCadastroPerfil> dadosCadastroPerfilJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoPerfil> dadosDetalhamentoPerfilJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroUsuario> dadosCadastroUsuarioJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoUsuario> dadosDetalhamentoUsuarioJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroCurso> dadosCadastroCursoJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoCurso> dadosDetalhamentoCursoJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroTopico> dadosCadastroTopicoJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoResumidoTopico> dadosDetalhamentoResumidoTopicoJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroResposta> dadosCadastroRespostaJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoResposta> dadosDetalhamentoRespostaJacksonTester;

    @MockBean
    private PerfilService perfilService;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private CursoService cursoService;

    @MockBean
    private TopicoService topicoService;

    @MockBean
    private RespostaService respostaService;

    @Test
    @DisplayName("Cadastro de perfil: deveria devolver 201")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void cadastrar_perfil() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        var dadosDetalhamentoPerfil = new DadosDetalhamentoPerfil(null, "COMUM");

        when(perfilService.cadastrar(any(), any())).thenReturn(dadosDetalhamentoPerfil);

        var response = mockMvc
                .perform(
                        post("/perfis")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroPerfilJacksonTester.write(
                                        new DadosCadastroPerfil("COMUM")
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoPerfilJacksonTester.write(
                dadosDetalhamentoPerfil
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Cadastro de usuários: deveria devolver 201")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void cadastrar_usuario() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        Perfil perfil = new Perfil("ADMIN");

        Usuario usuarioMock =
                new Usuario(2L, "Renan", "renan@example.com",
                "123456", perfil);

        BDDMockito.given(usuarioService.cadastrar(Mockito.any(DadosCadastroUsuario.class)))
                .willReturn(usuarioMock);

        DadosDetalhamentoUsuario dadosDetalhamentoUsuario = new DadosDetalhamentoUsuario(usuarioMock);

        var response = mockMvc
                .perform(
                        post("/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroUsuarioJacksonTester.write(
                                        new DadosCadastroUsuario("Renan", "renan@example.com",
                                                "123456", "COMUM")
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoUsuarioJacksonTester.write(
                dadosDetalhamentoUsuario
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Cadastro de curso: deveria devolver 201")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void cadastrar_curso() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        var dadosDetalhamentoCurso = new DadosDetalhamentoCurso(null, "Java", Categoria.TECNOLOGIA);

        when(cursoService.cadastrar(any(), any())).thenReturn(dadosDetalhamentoCurso);

        var response = mockMvc
                .perform(
                        post("/cursos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroCursoJacksonTester.write(
                                        new DadosCadastroCurso("Java", Categoria.TECNOLOGIA)
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoCursoJacksonTester.write(
                dadosDetalhamentoCurso
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Cadastro de tópico: deveria devolver 201")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void cadastrar_topico() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        var dadosDetalhamentoTopico = new DadosDetalhamentoResumidoTopico(
                null,
                "Título",
                "Mensagem",
                LocalDateTime.now());

        when(topicoService.criar(any(), any())).thenReturn(dadosDetalhamentoTopico);

        var response = mockMvc
                .perform(
                        post("/topicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroTopicoJacksonTester.write(
                                        new DadosCadastroTopico(
                                                "Título",
                                                "Mensagem",
                                                StatusTopico.NAO_RESPONDIDO,
                                                "Java"
                                        )
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoResumidoTopicoJacksonTester.write(
                dadosDetalhamentoTopico
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Cadastro de respostas: deveria devolver 201")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void cadastrar_repostas() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        var dadosDetalhamentoResposta = new DadosDetalhamentoResposta(
                null,
                "Mensagem",
                "Tópico",
                LocalDateTime.now());

        when(respostaService.cadastrar(any(), any(), any())).thenReturn(dadosDetalhamentoResposta);

        var response = mockMvc
                .perform(
                        post("/respostas/topicos/1/respostas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroRespostaJacksonTester.write(
                                        new DadosCadastroResposta(
                                                "Mensagem"
                                        )
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoRespostaJacksonTester.write(
                dadosDetalhamentoResposta
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
