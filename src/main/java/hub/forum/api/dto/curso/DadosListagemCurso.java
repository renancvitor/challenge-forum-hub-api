package hub.forum.api.dto.curso;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;

public record DadosListagemCurso(String nome,
                                 Categoria categoria) {

    public DadosListagemCurso(Curso curso) {
        this(
                curso.getNome(),
                curso.getCategoria()
        );
    }
}
