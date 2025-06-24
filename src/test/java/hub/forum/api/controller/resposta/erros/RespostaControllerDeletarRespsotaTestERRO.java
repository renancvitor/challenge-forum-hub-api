package hub.forum.api.controller.resposta.erros;

import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.usuario.Usuario;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RespostaControllerDeletarRespsotaTestERRO {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RespostaService respostaService;

    @Test
    @DisplayName("Deveria retornar 403 quando usuário não tiver perfil compatível com ação")
    void deletarResposta() throws Exception {
        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setId(1L);
        usuarioLogado.setNome("Jonas");
        usuarioLogado.setEmail("jonas@example.com");
        usuarioLogado.setSenha("123456");
        usuarioLogado.setPerfil(new Perfil("COMUM"));

        var auth = new UsernamePasswordAuthenticationToken(
                usuarioLogado,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_COMUM"))
        );

        doThrow(new AutorizacaoException("Apenas ADMIN ou MODERADOR podem deletar uma resposta"))
                .when(respostaService)
                .deletarResposta(2L, usuarioLogado);

        mockMvc.perform(delete("/respostas/2")
                .with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
                .andExpect(status().isForbidden());

        verify(respostaService).deletarResposta(2L, usuarioLogado);
    }
}