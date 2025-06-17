package hub.forum.api.dto.resposta;

import hub.forum.api.domain.resposta.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id,
                                        String mensagem,
                                        String topicoTitulo,
                                        LocalDateTime dataCriacao) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getTitulo(),
                resposta.getDataCriacao()
        );
    }
}
