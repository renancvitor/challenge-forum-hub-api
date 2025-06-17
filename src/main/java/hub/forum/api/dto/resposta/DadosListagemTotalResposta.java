package hub.forum.api.dto.resposta;

import hub.forum.api.domain.resposta.Resposta;

import java.time.LocalDateTime;

public record DadosListagemTotalResposta(Long id,
                                         String mensagem,
                                         String tituloTopico,
                                         LocalDateTime dataCriacao) {

    public DadosListagemTotalResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getTitulo(),
                resposta.getDataCriacao()
        );
    }
}
