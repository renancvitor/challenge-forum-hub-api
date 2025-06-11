package hub.forum.api.domain.resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(Long id,
                                    String mensagem,
                                    String tituloTopico,
                                    LocalDateTime dataCriacao,
                                    String nomeAutor,
                                    Boolean solucao) {

    public DadosListagemResposta(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getTitulo(),
                resposta.getDataCriacao(),
                resposta.getAutor().getNome(),
                resposta.getSolucao()
        );
    }
}
