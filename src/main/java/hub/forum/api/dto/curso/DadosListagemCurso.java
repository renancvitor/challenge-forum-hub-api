package hub.forum.api.dto.curso;

import hub.forum.api.domain.categoria.Categoria;
import hub.forum.api.domain.curso.Curso;

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
