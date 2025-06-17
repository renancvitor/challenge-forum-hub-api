package hub.forum.api.dto.resposta;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroResposta(@NotBlank(message = "Mensagem não pode ser vazia")
                                    String mensagem) {
}
