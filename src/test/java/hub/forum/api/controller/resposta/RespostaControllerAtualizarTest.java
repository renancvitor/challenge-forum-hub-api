package hub.forum.api.controller.resposta;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.DadosAtualizacaoResposta;
import hub.forum.api.dto.resposta.DadosDetalhamentoResposta;
import hub.forum.api.service.RespostaService;
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
class RespostaControllerAtualizarTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RespostaService respostaService;

    @Autowired
    private JacksonTester<DadosAtualizacaoResposta> dadosAtualizacaoRespostaJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoResposta> dadosDetalhamentoRespostaJacksonTester;

    @Test
    @DisplayName("Atualizar reposta: deveria devolver 200")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void atualizar() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        var dadosDetalhamentoResposta = new DadosDetalhamentoResposta(
                null,
                "Mensagem",
                "Tópico Título",
                LocalDateTime.now());

        when(respostaService.atualizar(any(), any(), any())).thenReturn(dadosDetalhamentoResposta);

        var response = mockMvc
                .perform(
                        put("/respostas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAtualizacaoRespostaJacksonTester.write(
                                        new DadosAtualizacaoResposta(
                                                "Mensagem att"
                                        )
                                ).getJson())
                ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoRespostaJacksonTester.write(
                dadosDetalhamentoResposta
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}