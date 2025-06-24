package hub.forum.api.controller.topico.erros;

import com.fasterxml.jackson.databind.ObjectMapper;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.DadosAtualizacaoTopico;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.service.TopicoService;
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
class TopicoControllerAtualizarTestERRO {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicoService topicoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deveria retornar 400 quando usuário não for o autor do tópico")
    void atualizar() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setId(1L);
        usuarioLogado.setNome("Renan");
        usuarioLogado.setEmail("renan@example.com");
        usuarioLogado.setSenha("123456");
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        var dadosAtualizacaoTopico = new DadosAtualizacaoTopico(
                "Título",
                "Mensagem"
        );

        var json = objectMapper.writeValueAsString(dadosAtualizacaoTopico);

        var auth = new UsernamePasswordAuthenticationToken(
                usuarioLogado,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        doThrow(new ValidacaoException("Apenas o autor do tópico pode marcar uma resposta como solução"))
                .when(topicoService)
                .atualizar(5L, dadosAtualizacaoTopico, usuarioLogado);

        mockMvc.perform(put("/topicos/5")
                        .contentType("application/json")
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
                .andExpect(status().isBadRequest());

        verify(topicoService).atualizar(5L, dadosAtualizacaoTopico, usuarioLogado);
    }
}