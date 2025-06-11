package hub.forum.api.domain.topico;

import hub.forum.api.domain.resposta.DadosListagemResposta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemTopico(Long id,
                                  String titulo,
                                  String mensagem,
                                  LocalDateTime dataCriacao,
                                  StatusTopico status,
                                  String nomeAutor,
                                  String nomeCurso,
                                  List<DadosListagemResposta> respostas) {

    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                topico.getRespostas().stream()
                        .map(DadosListagemResposta::new)
                        .collect(Collectors.toList())
        );
    }
}
