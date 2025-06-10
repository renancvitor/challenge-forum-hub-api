package hub.forum.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCriarTopico(String titulo,
                               @NotBlank
                               String mensagem,
                               StatusTopico status,
                               @NotNull
                               @JsonAlias("usuario_id")
                               Long autorId,
                               @NotNull
                               @JsonAlias("curso_id")
                               Long cursoId) {
}
