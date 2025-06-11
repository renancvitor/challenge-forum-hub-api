package hub.forum.api.dto.perfil;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPerfil(@NotBlank(message = "O nome do perfil é obrigatório")
                                  String nome) {
}
