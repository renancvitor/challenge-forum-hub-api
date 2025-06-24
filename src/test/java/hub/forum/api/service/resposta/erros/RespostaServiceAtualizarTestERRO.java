package hub.forum.api.service.resposta.erros;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.perfil.Perfil;
import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.DadosAtualizacaoResposta;
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

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RespostaServiceAtualizarTestERRO {

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

        var resposta = new Resposta();
        resposta.setTopico(topico);
        resposta.setMensagem("Mensagem atualiza");
        resposta.setDataCriacao(LocalDateTime.now());
        resposta.setAutor(usuarioSalvo);
        resposta.setAtivo(true);
        resposta.setSolucao(false);
        respostaRepository.save(resposta);

        var dadosInvalidos = new DadosAtualizacaoResposta("");

        assertThrows(ValidacaoException.class, () -> {
            respostaService.atualizar(resposta.getId(), dadosInvalidos, usuarioSalvo);
        });
    }

    @Test
    @Transactional
    void atualizarAutorizacaoException() {
        var perfil = perfilRepository.save(new Perfil("USUARIO"));

        var autor = new Usuario();
        autor.setNome("Autor");
        autor.setEmail("autor@example.com");
        autor.setSenha("123456");
        autor.setPerfil(perfil);
        Usuario autorSalvo = usuarioRepository.save(autor);

        var usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setEmail("usuario@example.com");
        usuario.setSenha("123456");
        usuario.setPerfil(perfil);
        Usuario outroUsuarioSalvo = usuarioRepository.save(usuario);

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
        resposta.setAutor(autorSalvo);
        resposta.setAtivo(true);
        resposta.setSolucao(false);
        respostaRepository.save(resposta);

        var dadosAtualizados = new DadosAtualizacaoResposta("Mensagem");

        assertThrows(AutorizacaoException.class, () -> {
            respostaService.atualizar(resposta.getId(), dadosAtualizados, outroUsuarioSalvo);
        });
    }
}