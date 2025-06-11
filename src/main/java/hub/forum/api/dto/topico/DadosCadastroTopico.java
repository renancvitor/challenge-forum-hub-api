package hub.forum.api.dto.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import hub.forum.api.domain.topico.StatusTopico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(@NotBlank(message = "Título não pode ser vazio")
                                  String titulo,
                                  @NotBlank(message = "Mensagem não pode ser vazia")
                                  String mensagem,
                                  StatusTopico status,
                                  @NotNull(message = "Não pode ser vazio")
                                  @JsonAlias("usuario_id")
                                  Long autorId,
                                  @NotNull(message = "Não pode ser vazio")
                                  @JsonAlias("curso_id")
                                  Long cursoId) {
}
