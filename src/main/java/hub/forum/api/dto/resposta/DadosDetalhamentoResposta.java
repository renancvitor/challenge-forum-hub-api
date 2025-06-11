package hub.forum.api.dto.resposta;

import hub.forum.api.domain.resposta.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id,
                                        String mensagem,
                                        Long topicoId,
                                        LocalDateTime dataCriacao,
                                        Long autorId,
                                        Boolean solucao) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getId(),
                resposta.getDataCriacao(),
                resposta.getAutor().getId(),
                resposta.getSolucao()
        );
    }
}
