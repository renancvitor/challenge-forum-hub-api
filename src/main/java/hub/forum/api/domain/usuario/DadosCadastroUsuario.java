package hub.forum.api.domain.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(@NotBlank(message = "Nome é obrigatório")
                                   String nome,
                                   @NotBlank(message = "Email é obrigatório")
                                   @Email(message = "Formato inválido")
                                   String email,
                                   @NotBlank
                                   String senha,
                                   @NotNull(message = "Perfil é obnigatório")
                                   @Valid
                                   DadosCadastroPerfil perfil) {
}
