package hub.forum.api.domain.curso;

import hub.forum.api.domain.categoria.Categoria;

public record DadosListagemCurso(Long id,
                                 String nome,
                                 Categoria categoria) {

    public DadosListagemCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNome(),
                curso.getCategoria()
        );
    }
}
