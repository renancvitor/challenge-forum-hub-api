package hub.forum.api.topico;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.DadosAtualizacaoTopico;
import hub.forum.api.repository.CursoRepository;
import hub.forum.api.repository.PerfilRepository;
import hub.forum.api.repository.TopicoRepository;
import hub.forum.api.repository.UsuarioRepository;
import hub.forum.api.service.TopicoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TopicoServiceAtualizarTest {

    @Autowired
    TopicoRepository topicoRepository;

    @Autowired
    TopicoService topicoService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Test
    @Transactional
    void atualizar() {
        var perfil = perfilRepository.save(new Perfil("USUARIO"));

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

        var dadosAtualizados = new DadosAtualizacaoTopico("Teste atualizado",
                "Mensagem atualizada");
        var resultado = topicoService.atualizar(
                topico.getId(),
                dadosAtualizados,
                usuario
        );

        assertNotNull(resultado);
        assertEquals("Teste atualizado", resultado.titulo());
        assertEquals("Mensagem atualizada", resultado.mensagem());
    }
}