package hub.forum.api.service;

import hub.forum.api.domain.curso.Curso;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.topico.*;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.CursoRepository;
import hub.forum.api.repository.TopicoRepository;
import hub.forum.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public DadosDetalhamentoResumidoTopico criar(DadosCadastroTopico dados, Usuario autor) {
        Curso curso = cursoRepository.findByNome(dados.cursoNome())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        Topico topico = new Topico(dados, autor, curso);
        topico = topicoRepository.save(topico);

        return new DadosDetalhamentoResumidoTopico(topico);
    }

    public Page<DadosListagemTotalTopico> listar(Pageable paginacao) {
        return topicoRepository.findAllAtivos(paginacao).map(DadosListagemTotalTopico::new);
    }

    public DadosListagemUnicoTopico listarById(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));
        return new DadosListagemUnicoTopico(topico);
    }

    @Transactional
    public DadosDetalhamentoResumidoTopico atualizar(Long id, DadosAtualizacaoTopico dados,
                                                     Usuario usuario) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico com ID " + id + " não encontrado"));

        if (!topico.getAutor().getId().equals(usuario.getId())) {
            throw new ValidacaoException("Apenas o autor pode atualizar o tópico");
        }

        if ((dados.titulo() == null || dados.titulo().isBlank()) &&
                (dados.mensagem() == null || dados.mensagem().isBlank())) {
            throw new ValidacaoException("É necessário alterar o título ou a mensagem");
        }

        topico.atualizarTopico(dados);
        return new DadosDetalhamentoResumidoTopico(topico);
    }

    @Transactional
    public void deletarTopico(Long topicoId, Usuario autor) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));

        String nomePerfil = autor.getPerfil().getNome();

        if (!nomePerfil.equals("ADMIN") && !nomePerfil.equals("MODERADOR")) {
            throw new ValidacaoException("Apenas ADMIN ou MODERADOR podem deletar um tópico");
        }

        topico.setAtivo(false);
    }
}
