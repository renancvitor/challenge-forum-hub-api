package hub.forum.api.dto.curso;

import hub.forum.api.domain.categoria.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso(@NotBlank(message = "Nome do curso é obrigatório")
                                 String nome,
                                 @NotNull(message = "Categoria é obrigatório")
                                 Categoria categoria) {
}
