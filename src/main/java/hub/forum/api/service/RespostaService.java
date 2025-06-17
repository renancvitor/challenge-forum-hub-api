package hub.forum.api.service;

import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.dto.resposta.*;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.RespostaRepository;
import hub.forum.api.repository.TopicoRepository;
import hub.forum.api.repository.UsuarioRepository;
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

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Transactional
    public DadosDetalhamentoResposta cadastrar(Long topicoId, String mensagem) {
        Usuario autor = usuarioLogadoService.getUsuario();
        Topico topico = topicoRepository.getReferenceById(topicoId);

        Resposta resposta = new Resposta(mensagem, topico, autor); // você pode ajustar o construtor
        respostaRepository.save(resposta);
        return new DadosDetalhamentoResposta(resposta);
    }

    public Page<DadosListagemTotalResposta> listar(Pageable paginacao) {
        return respostaRepository.findAll(paginacao).map(DadosListagemTotalResposta::new);
    }

    @Transactional
    public DadosDetalhamentoResumidoResposta atualizar(Long id, DadosAtualizacaoResposta dados) {
        var resposta = respostaRepository.findById(id)
                        .orElseThrow(() -> new ValidacaoException("Resposta com ID " + id + " não encontrado"));
        resposta.atualizarResposta(dados);
        return new DadosDetalhamentoResumidoResposta(resposta);
    }

    @Transactional
    public void marcarSolucao(Long idResposta, Long idTopico, Long idUsuarioLogado) {
        Resposta resposta = respostaRepository.findById(idResposta)
                .orElseThrow(() -> new ValidacaoException("Resposta não encontrada"));

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
    public void deletarResposta(Long repostaId, Usuario usuario) {
        Resposta resposta = respostaRepository.findById(repostaId)
                .orElseThrow(() -> new ValidacaoException("Resposta não encontrado"));

        String nomePerfil = usuario.getPerfil().getNome();

        if (!nomePerfil.equals("ADMIN") && !nomePerfil.equals("MODERADOR")) {
            throw new ValidacaoException("Apenas ADMIN ou MODERADOR podem deletar uma resposta");
        }

        resposta.setAtivo(false);
    }
}
