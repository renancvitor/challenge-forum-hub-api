package hub.forum.api.controller.topico.erros;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.infra.exception.AutorizacaoException;
import hub.forum.api.infra.exception.ValidacaoException;
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
class TopicoControllerMarcarSolucaoTestERRO {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RespostaService respostaService;

    @Test
    @DisplayName("Deveria retornar 403 quando usuário não for o autor do tópico")
    void marcarRespostaComoSolucao() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setId(1L);
        usuarioLogado.setNome("Renan");
        usuarioLogado.setEmail("renan@example.com");
        usuarioLogado.setSenha("123456");
        usuarioLogado.setPerfil(new Perfil("ADMIN"));

        var auth = new UsernamePasswordAuthenticationToken(
                usuarioLogado,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        doThrow(new AutorizacaoException("Apenas o autor do tópico pode marcar uma resposta como solução"))
                .when(respostaService)
                .marcarSolucao(6L, 3L, 1L);

        mockMvc.perform(put("/topicos/3/resposta/6/solucao")
                        .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
                .andExpect(status().isForbidden());

        verify(respostaService).marcarSolucao(6L, 3L, 1L);
    }
}
