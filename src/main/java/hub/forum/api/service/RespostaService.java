package hub.forum.api.service;

import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import hub.forum.api.infra.exception.ValidacaoException;
import hub.forum.api.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoService topicoService;

    @Transactional
    public void marcarSolucao(Long idResposta, Long autorId, Long idTopico) {
        Resposta resposta = respostaRepository.findById(idResposta)
                .orElseThrow(() -> new ValidacaoException("Resposta não encontrada"));

        if (!resposta.getTopico().getId().equals(idTopico)) {
            throw new ValidacaoException("Resposta não pertence a este tópico");
        }

        Topico topico = resposta.getTopico();

        topicoService.validarResposta(topico.getId(), autorId);

        if (topico.getStatus() == StatusTopico.SOLUCIONADO) {
            resposta.marcarComoSolucao();
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
