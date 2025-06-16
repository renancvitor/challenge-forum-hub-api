package hub.forum.api.dto.topico;

import hub.forum.api.domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoResumidoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao) {

    public DadosDetalhamentoResumidoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao()
        );
    }
}
