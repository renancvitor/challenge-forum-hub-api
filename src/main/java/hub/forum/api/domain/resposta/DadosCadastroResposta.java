package hub.forum.api.domain.resposta;

public record DadosCadastroResposta(String mensagem,
                                    Long topicoId,
                                    Long autorId) {
}
