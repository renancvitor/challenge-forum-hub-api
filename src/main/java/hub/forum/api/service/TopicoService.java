package hub.forum.api.service;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.DadosAtualizacaoTopico;
import hub.forum.api.dto.topico.DadosCadastroTopico;
import hub.forum.api.dto.topico.DadosDetalhamentoTopico;
import hub.forum.api.dto.topico.DadosListagemTopico;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.CursoRepository;
import hub.forum.api.repository.TopicoRepository;
import hub.forum.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public DadosDetalhamentoTopico criar(DadosCadastroTopico dados) {
        Usuario autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new ValidacaoException("Autor não encontrado"));
        Curso curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new ValidacaoException("Curso não encontrado"));

        Topico topico = new Topico(dados, autor, curso);
        topico = topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public Page<DadosListagemTopico> listar(Pageable paginacao) {
        return topicoRepository.findAllAtivos(paginacao).map(DadosListagemTopico::new);
    }

    public DadosDetalhamentoTopico listarById(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public void receberResposta(Long topicoId) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ValidacaoException("Tõpico não encontrado"));

        topico.setStatus(StatusTopico.NAO_SOLUCIONADO);
    }

    @Transactional
    public void validarResposta(Long topicoId, Long autorId) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));

        if (!topico.getAutor().getId().equals(autorId)) {
            throw new ValidacaoException("Apenas o autor pode validar a resposta");
        }

        topico.setStatus(StatusTopico.SOLUCIONADO);
        topicoRepository.save(topico);
    }

    @Transactional
    public DadosDetalhamentoTopico atualizar(DadosAtualizacaoTopico dados) {
        var topico = topicoRepository.findById(dados.id())
                .orElseThrow(() -> new ValidacaoException("Tópico com ID " + dados.id() + " não encontrado"));
        topico.atualizarResposta(dados);
        return new DadosDetalhamentoTopico(topico);
    }

    @Transactional
    public void deletarTopico(Long topicoId, Usuario usuario) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ValidacaoException("Tópico não encontrado"));

        String nomePerfil = usuario.getPerfil().getNome();

        if (!nomePerfil.equals("ADMIN") && !nomePerfil.equals("MODERADOR")) {
            throw new ValidacaoException("Apenas ADMIN ou MODERADOR podem deletar um tópico");
        }

        topico.setAtivo(false);
    }
}
