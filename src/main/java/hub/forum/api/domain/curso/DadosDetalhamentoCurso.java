package hub.forum.api.domain.curso;

import hub.forum.api.domain.categoria.Categoria;

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
