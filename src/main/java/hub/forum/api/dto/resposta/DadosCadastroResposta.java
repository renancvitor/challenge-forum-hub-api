package hub.forum.api.dto.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(@NotBlank(message = "Mensagem não pode ser vazia")
                                    String mensagem,
                                    @NotNull
                                    Long topicoId,
                                    @NotNull
                                    Long autorId) {
}
