package hub.forum.api.dto.resposta;

import hub.forum.api.domain.resposta.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResumidoResposta(Long id,
                                                String mensagem,
                                                Long topicoId,
                                                LocalDateTime dataCriacao) {

    public DadosDetalhamentoResumidoResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getId(),
                resposta.getDataCriacao()
        );
    }
}
