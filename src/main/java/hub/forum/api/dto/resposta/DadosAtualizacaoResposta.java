package hub.forum.api.dto.resposta;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoResposta(@NotBlank(message = "Não pode ser vazio")
                                       String mensagem) {
}
