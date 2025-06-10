package hub.forum.api.domain.resposta;

public record DadosResposta(String mensagem,
                            Long topicoId,
                            Long autorId,
                            Boolean solucao) {
}
