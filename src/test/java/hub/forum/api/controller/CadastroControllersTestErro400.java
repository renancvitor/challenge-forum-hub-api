package hub.forum.api.controller;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.dto.curso.DadosCadastroCurso;
import hub.forum.api.dto.perfil.DadosCadastroPerfil;
import hub.forum.api.dto.resposta.DadosCadastroResposta;
import hub.forum.api.dto.topico.DadosCadastroTopico;
import hub.forum.api.dto.usuario.DadosCadastroUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CadastroControllersTestErro400 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosCadastroUsuario> dadosCadastroUsuarioJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroPerfil> dadosCadastroPerfilJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroCurso> dadosCadastroCursoJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroResposta> dadosCadastroRespostaJacksonTester;

    @Autowired
    private JacksonTester<DadosCadastroTopico> dadosCadastroTopicoJacksonTester;

    @Test
    @DisplayName("Cadastro de usuário: deveria devolver 400 quando informações inválidas")
    @WithMockUser
    void cadastrar_usuario() throws Exception {

        var response = mockMvc
                .perform(
                        post("/usuarios")
                                .contentType("application/json")
                                .content(dadosCadastroUsuarioJacksonTester.write(
                                        new DadosCadastroUsuario("", "", "", "")
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Cadastro de perfil: deveria devolver 400 quando informações inválidas")
    @WithMockUser
    void cadastrar_perfil() throws Exception {

        var response = mockMvc
                .perform(
                        post("/perfis")
                                .contentType("application/json")
                                .content(dadosCadastroPerfilJacksonTester.write(
                                        new DadosCadastroPerfil("")
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Cadastro de curso: deveria devolver 400 quando informações inválidas")
    @WithMockUser
    void cadastrar_curso() throws Exception {

        var response = mockMvc
                .perform(
                        post("/cursos")
                                .contentType("application/json")
                                .content(dadosCadastroCursoJacksonTester.write(
                                        new DadosCadastroCurso("", Categoria.TECNOLOGIA)
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Cadastro de topico: deveria devolver 400 quando informações inválidas")
    @WithMockUser
    void cadastrar_topico() throws Exception {

        var response = mockMvc
                .perform(
                        post("/topicos")
                                .contentType("application/json")
                                .content(dadosCadastroTopicoJacksonTester.write(
                                        new DadosCadastroTopico("", "",
                                                StatusTopico.NAO_RESPONDIDO, "")
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Cadastro de resposta: deveria devolver 400 quando informações inválidas")
    @WithMockUser
    void cadastrar_resposta() throws Exception {

        var response = mockMvc
                .perform(
                        post("/respostas/topicos/9/respostas")
                                .contentType("application/json")
                                .content(dadosCadastroRespostaJacksonTester.write(
                                        new DadosCadastroResposta("")
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
