package hub.forum.api.controller.topico.acertivos;

import hub.forum.api.service.TopicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TopicoControllerDeletarTopicoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicoService topicoService;

    @Test
    @DisplayName("Deletar tópico: deveria devolver 204")
    void deletarTopico() throws Exception {
        mockMvc.perform(delete("/topicos/3")
                        .with(user("renan").roles("ADMIN")))
                .andExpect(status().isNoContent());
    }
}