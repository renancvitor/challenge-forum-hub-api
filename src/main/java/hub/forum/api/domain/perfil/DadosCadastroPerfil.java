package hub.forum.api.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPerfil(@NotBlank String nome) {
}
