package hub.forum.api.dto.curso;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;

public record DadosDetalhamentoCurso(Long id,
                                     String nome,
                                     Categoria categoria) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNome(),
                curso.getCategoria()
        );
    }
}
