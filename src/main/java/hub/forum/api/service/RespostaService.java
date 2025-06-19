package hub.forum.api.service;

import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.DadosAtualizacaoResposta;
import hub.forum.api.dto.resposta.DadosDetalhamentoResposta;
import hub.forum.api.dto.resposta.DadosListagemTotalResposta;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.RespostaRepository;
import hub.forum.api.repository.TopicoRepository;
import hub.forum.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoService topicoService;

    @Transactional
    public DadosDetalhamentoResposta cadastrar(Long topicoId, String mensagem, Usuario autor) {
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new EntityNotFoundException("Topico não encontrado."));

        Resposta resposta = new Resposta(mensagem, topico, autor);
        respostaRepository.save(resposta);

        if (topico.getStatus() == StatusTopico.NAO_RESPONDIDO) {
            topico.setStatus(StatusTopico.NAO_SOLUCIONADO);
            topicoRepository.save(topico);
        }

        return new DadosDetalhamentoResposta(resposta);
    }

    public Page<DadosListagemTotalResposta> listar(Pageable paginacao) {
        return respostaRepository.findAll(paginacao).map(DadosListagemTotalResposta::new);
    }

    @Transactional
    public DadosDetalhamentoResposta atualizar(Long id, DadosAtualizacaoResposta dados,
                                               Usuario usuario) {
        var resposta = respostaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Resposta com ID " + id + " não encontrado"));

        if (!resposta.getAutor().getId().equals(usuario.getId())) {
            throw new ValidacaoException("Apenas o autor pode atualizar a resposta");
        }

        resposta.atualizarResposta(dados);
        return new DadosDetalhamentoResposta(resposta);
    }

    @Transactional
    public void marcarSolucao(Long idResposta, Long idTopico, Long idUsuarioLogado) {
        Resposta resposta = respostaRepository.findById(idResposta)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada"));

        Topico topico = resposta.getTopico();

        if (!topico.getId().equals(idTopico)) {
            throw new ValidacaoException("Resposta não pertence a este tópico");
        }

        if (!topico.getAutor().getId().equals(idUsuarioLogado)) {
            throw new ValidacaoException("Apenas o autor do tópico pode marcar uma resposta como solução");
        }

        if (topico.getStatus() != StatusTopico.SOLUCIONADO) {
            resposta.marcarComoSolucao();
            topico.setStatus(StatusTopico.SOLUCIONADO);
        }
    }

    @Transactional
    public void deletarResposta(Long respostaId, Usuario usuario) {
        Resposta resposta = respostaRepository.findById(respostaId)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não encontrado"));

        String nomePerfil = usuario.getPerfil().getNome();

        if (!nomePerfil.equals("ADMIN") && !nomePerfil.equals("MODERADOR")) {
            throw new ValidacaoException("Apenas ADMIN ou MODERADOR podem deletar uma resposta");
        }

        resposta.setAtivo(false);
    }
}
