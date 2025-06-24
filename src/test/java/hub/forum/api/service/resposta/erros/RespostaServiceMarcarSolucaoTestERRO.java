package hub.forum.api.service.resposta.erros;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.infra.exception.AutorizacaoException;
import hub.forum.api.infra.exception.ValidacaoException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RespostaServiceMarcarSolucaoTestERRO {

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
    void marcarSolucaoValidacaoException() {
        var perfil = perfilRepository.save(new Perfil("ADMIN"));

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

        var topico1 = new Topico();
        topico1.setTitulo("Teste 01");
        topico1.setMensagem("Mensagem 01");
        topico1.setDataCriacao(LocalDateTime.now());
        topico1.setStatus(StatusTopico.NAO_RESPONDIDO);
        topico1.setAutor(usuario);
        topico1.setCurso(curso);
        topico1.setAtivo(true);
        topicoRepository.save(topico1);

        var topico2 = new Topico();
        topico2.setTitulo("Topico 02");
        topico2.setMensagem("Mensagem 02");
        topico2.setDataCriacao(LocalDateTime.now());
        topico2.setStatus(StatusTopico.NAO_RESPONDIDO);
        topico2.setAutor(usuario);
        topico2.setCurso(curso);
        topico2.setAtivo(true);
        topicoRepository.save(topico2);

        var resposta = new Resposta();
        resposta.setTopico(topico1);
        resposta.setMensagem("Mensagem atualiza");
        resposta.setDataCriacao(LocalDateTime.now());
        resposta.setAutor(usuario);
        resposta.setAtivo(true);
        resposta.setSolucao(false);
        respostaRepository.save(resposta);
        assertTrue(resposta.getAtivo());

        assertThrows(ValidacaoException.class, () -> {
            respostaService.marcarSolucao(
                    resposta.getId(),
                    topico2.getId(),
                    usuarioSalvo.getId()
            );
        });
    }

    @Test
    @Transactional
    void marcarSolucaoAutorizacaoException() {
        var perfil = perfilRepository.save(new Perfil("ADMIN"));

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
        topico.setTitulo("Teste");
        topico.setMensagem("Mensagem");
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(StatusTopico.NAO_RESPONDIDO);
        topico.setAutor(outroUsuarioSalvo);
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

        assertThrows(AutorizacaoException.class, () -> {
            respostaService.marcarSolucao(
                    resposta.getId(),
                    topico.getId(),
                    usuarioSalvo.getId()
            );
        });
    }
}