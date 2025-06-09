package hub.forum.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPerfil(@NotBlank String nome) {
}
