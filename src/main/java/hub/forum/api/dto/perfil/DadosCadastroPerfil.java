package hub.forum.api.dto.perfil;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPerfil(@NotBlank String nome) {
}
