package hub.forum.api.controller.topico;

import com.fasterxml.jackson.databind.ObjectMapper;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.DadosListagemUnicoTopico;
import hub.forum.api.service.TopicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerListarByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicoService topicoService;

    @Autowired
    private JacksonTester<DadosListagemUnicoTopico> dadosListagemUnicoTopicoJacksonTester;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Listar único tópico: deveria devolver 200")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void listarById() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        var dadosListagemUnicoTopico = new DadosListagemUnicoTopico(
                null,
                "Título",
                "Mensagem",
                LocalDateTime.now(),
                StatusTopico.NAO_RESPONDIDO,
                "Renan",
                null);

        when(topicoService.listarById(any())).thenReturn(dadosListagemUnicoTopico);

        var response = mockMvc
                .perform(get("/topicos/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosListagemUnicoTopicoJacksonTester.write(
                dadosListagemUnicoTopico).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}