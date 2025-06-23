package hub.forum.api.controller.topico.acertivos;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.DadosAtualizacaoTopico;
import hub.forum.api.dto.topico.DadosDetalhamentoResumidoTopico;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerAtualizarTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosAtualizacaoTopico> dadosAtualizacaoTopicoJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoResumidoTopico> dadosDetalhamentoResumidoTopicoJacksonTester;

    @MockBean
    private TopicoService topicoService;

    @Test
    @DisplayName("Atualizar tópico: deveria devolver 200")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void atualizar() throws Exception {
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

        when(topicoService.atualizar(any(), any(), any())).thenReturn(dadosDetalhamentoTopico);

        var response = mockMvc
                .perform(
                        put("/topicos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAtualizacaoTopicoJacksonTester.write(
                                        new DadosAtualizacaoTopico(
                                                "Titulo att",
                                                "Mensagem att"
                                        )
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoResumidoTopicoJacksonTester.write(
                dadosDetalhamentoTopico
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}