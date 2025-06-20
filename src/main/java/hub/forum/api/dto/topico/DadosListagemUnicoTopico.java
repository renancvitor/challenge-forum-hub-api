package hub.forum.api.dto.topico;

import hub.forum.api.domain.topico.StatusTopico;
import hub.forum.api.domain.topico.Topico;
import hub.forum.api.dto.resposta.DadosListagemTotalResposta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemUnicoTopico(Long id,
                                       String titulo,
                                       String mensagem,
                                       LocalDateTime dataCriacao,
                                       StatusTopico status,
                                       String nomeAutor,
                                       List<DadosListagemTotalResposta> respostas) {

    public DadosListagemUnicoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getRespostas().stream()
                        .map(DadosListagemTotalResposta::new)
                        .collect(Collectors.toList())
        );
    }
}
