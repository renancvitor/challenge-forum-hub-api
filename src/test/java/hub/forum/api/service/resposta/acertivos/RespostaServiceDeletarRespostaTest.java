package hub.forum.api.service.resposta.acertivos;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.repository.*;
import hub.forum.api.service.RespostaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RespostaServiceDeletarRespostaTest {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    RespostaRepository respostaRepository;

    @Autowired
    RespostaService respostaService;

    @Test
    @Transactional
    void deletarResposta() {
        var perfil = perfilRepository.save(new Perfil("ADMIN"));

        var usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setEmail("usuario@example.com");
        usuario.setSenha("123456");
        usuario.setPerfil(perfil);
        usuario = usuarioRepository.save(usuario);

        var curso = new Curso();
        curso.setNome("Spring Boot 3.0 atualiza");
        curso.setCategoria(Categoria.TECNOLOGIA);
        curso = cursoRepository.save(curso);

        var topico = new Topico();
        topico.setTitulo("Teste atualiza");
        topico.setMensagem("Mensagem atualiza");
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(StatusTopico.NAO_RESPONDIDO);
        topico.setAutor(usuario);
        topico.setCurso(curso);
        topico.setAtivo(true);
        topicoRepository.save(topico);

        var resposta = new Resposta();
        resposta.setTopico(topico);
        resposta.setMensagem("Mensagem atualiza");
        resposta.setDataCriacao(LocalDateTime.now());
        resposta.setAutor(usuario);
        resposta.setAtivo(true);
        resposta.setSolucao(false);
        respostaRepository.save(resposta);
        assertTrue(resposta.getAtivo());

        respostaService.deletarResposta(resposta.getId(), usuario);

        var respostaDeletada = respostaRepository.findById(resposta.getId())
                .orElseThrow();

        assertFalse(respostaDeletada.getAtivo());
    }
}