package hub.forum.api.dto.resposta;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(@NotNull
                                       Long id,
                                       String mensagem) {
}
