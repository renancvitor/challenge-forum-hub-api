package hub.forum.api.dto.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(String titulo,
                                     String mensagem) {
}
