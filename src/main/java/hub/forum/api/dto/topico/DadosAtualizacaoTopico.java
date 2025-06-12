package hub.forum.api.dto.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(@NotNull
                                     Long id,
                                     String mensagem) {
}
