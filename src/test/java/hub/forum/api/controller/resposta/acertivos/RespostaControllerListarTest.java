package hub.forum.api.controller.resposta.acertivos;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.DadosListagemTotalResposta;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class RespostaControllerListarTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RespostaService respostaService;

    @Autowired
    private JacksonTester<DadosListagemTotalResposta> dadosListagemTotalRespostaJacksonTester;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Listar respostas: deveria devolver 200")
    @WithMockUser(username = "renan", roles = {"ADMIN"})
    void listar() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(usuarioLogado);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        var dadosListagemResposta = new DadosListagemTotalResposta(
                null,
                "Mensagem",
                "Tópico Título",
                LocalDateTime.now());

        Page<DadosListagemTotalResposta> paginaMockada =
                new PageImpl<>(List.of(dadosListagemResposta), PageRequest.of(0, 10), 1);

        when(respostaService.listar(any())).thenReturn(paginaMockada);

        var response = mockMvc
                .perform(get("/respostas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        JsonNode jsonNode = objectMapper.readTree(response.getContentAsString());

        assertThat(jsonNode.get("content")).isNotNull();
        assertThat(jsonNode.get("content").isArray()).isTrue();
        assertThat(jsonNode.get("content").size()).isEqualTo(1);

        JsonNode primeiraResposta = jsonNode.get("content").get(0);
        assertThat(primeiraResposta.get("mensagem").asText()).isEqualTo("Mensagem");
    }
}