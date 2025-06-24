package hub.forum.api.service.topico.erros;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.DadosAtualizacaoTopico;
import hub.forum.api.infra.exception.AutorizacaoException;
import hub.forum.api.infra.exception.ValidacaoException;
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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TopicoServiceAtualizarTestERRO {

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
    void atualizarValidacaoException() {
        var perfil = perfilRepository.save(new Perfil("USUARIO"));

        var usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setEmail("usuario@example.com");
        usuario.setSenha("123456");
        usuario.setPerfil(perfil);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

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

        var dadosInvalidos = new DadosAtualizacaoTopico("", "");

        assertThrows(ValidacaoException.class, () -> {
            topicoService.atualizar(topico.getId(), dadosInvalidos, usuarioSalvo);
        });
    }

    @Test
    @Transactional
    void atualizarAutorizacaoException() {
        var perfil = perfilRepository.save(new Perfil("USUARIO"));

        var usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setEmail("usuario@example.com");
        usuario.setSenha("123456");
        usuario.setPerfil(perfil);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        var outroUsuario = new Usuario();
        outroUsuario.setNome("Outro");
        outroUsuario.setEmail("outro@example.com");
        outroUsuario.setSenha("123456");
        outroUsuario.setPerfil(perfil);
        Usuario outroUsuarioSalvo = usuarioRepository.save(outroUsuario);

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

        var dadosAtualizados = new DadosAtualizacaoTopico("Novo Titulo", "Nova Mensagem");

        assertThrows(AutorizacaoException.class, () -> {
            topicoService.atualizar(topico.getId(), dadosAtualizados, outroUsuarioSalvo);
        });
    }
}