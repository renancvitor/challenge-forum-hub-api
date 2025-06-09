package hub.forum.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosPerfil(@NotBlank String nome) {
}
