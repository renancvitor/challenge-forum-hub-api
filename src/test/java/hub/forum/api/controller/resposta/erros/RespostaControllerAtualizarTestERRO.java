package hub.forum.api.controller.resposta.erros;

import com.fasterxml.jackson.databind.ObjectMapper;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.DadosAtualizacaoResposta;
import hub.forum.api.infra.exception.AutorizacaoException;
import hub.forum.api.service.RespostaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RespostaControllerAtualizarTestERRO {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RespostaService respostaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deveria retornar 403 quando usuário não for o autor da resposta")
    void atualizar() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setId(1L);
        usuarioLogado.setNome("Renan");
        usuarioLogado.setEmail("renan@example.com");
        usuarioLogado.setSenha("123456");
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        var dadosAtualizacaoRespospota = new DadosAtualizacaoResposta(
                "Mensagem"
        );

        var json = objectMapper.writeValueAsString(dadosAtualizacaoRespospota);

        var auth = new UsernamePasswordAuthenticationToken(
                usuarioLogado,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        doThrow(new AutorizacaoException("Apenas o autor do tópico pode marcar uma resposta como solução"))
                .when(respostaService)
                .atualizar(1L, dadosAtualizacaoRespospota, usuarioLogado);

        mockMvc.perform(put("/respostas/1")
                .contentType("application/json")
                .content(json)
                .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
                .andExpect(status().isForbidden());

        verify(respostaService).atualizar(1L, dadosAtualizacaoRespospota, usuarioLogado);
    }
}